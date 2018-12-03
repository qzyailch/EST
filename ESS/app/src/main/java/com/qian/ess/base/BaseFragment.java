package com.qian.ess.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qian.ess.R;
import com.qian.ess.application.ESSApplication;
import com.qian.ess.base.callback.IFragmentBackHandled;
import com.qian.ess.common.Constants;
import com.qian.ess.widget.loadding.CustomDialog;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

public abstract class BaseFragment extends Fragment {

    protected String TAG;

    //activity跳转动画
    private Constants.ActivityAnimType animType = Constants.ActivityAnimType.DEFAULT;

    protected View mRootView;
    protected LayoutInflater inflater;

    protected Context mContext;

    private CustomDialog dialog;
    private ProgressDialog mLoadingDialog;

    protected IFragmentBackHandled mIFragmentBackHandled;
    protected boolean isLoaded = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((getActivity() instanceof IFragmentBackHandled)) {
            this.mIFragmentBackHandled = (IFragmentBackHandled) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutResId(), container, false);
        }
        mContext = getContext();
        TAG = this.getClass().getSimpleName();

        ButterKnife.bind(this, mRootView);

        Toolbar commonToolbar = ButterKnife.findById(mRootView, R.id.common_toolbar);
        if (commonToolbar != null) {
            ((BaseActivity) getActivity()).setSupportActionBar(commonToolbar);

            ActionBar actionBar = ((BaseActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowTitleEnabled(false); //隐藏自带的title
            }

            if (hasBackIcon()) {
                commonToolbar.setNavigationIcon(R.mipmap.com_back);
                commonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((BaseActivity) getActivity()).backward();
                    }
                });
            }

            setHasOptionsMenu(true);
            initToolBar(commonToolbar);
        }

        initViews();
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    /**
     * 设置是否加载出数据
     */
    public void setIsLoaded(boolean isLoaded) {
        this.isLoaded = isLoaded;
    }

    /**
     * fragment被设置为可见时调用
     */
    private void onVisiblePre() {
        if (null != mIFragmentBackHandled) {
            mIFragmentBackHandled.setSelectedFragment(this);
        }

        //如果已经加载完数据，则返回不继续执行，该值在具体业务中进行赋值，如果不进行赋值，则默认每次可见时都加载数据
        if (isLoaded) {
            return;
        }

        if (null != mRootView) {
            onVisible();
        }
    }

    /**
     * fragment被设置为可见时调用
     */
    protected void onVisible() {

    }

    /**
     * fragment被设置为不可见时调用
     */
    protected void onInvisible() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisiblePre();
        } else {
            onInvisible();
        }
    }

    /**
     * 重写的返回键,返回false表示Fragment不消耗该返回事件
     */
    public boolean onBackPressed() {
        return false;
    }

    @LayoutRes
    public abstract int getLayoutResId();

    public void initToolBar(Toolbar commonToolbar) {

    }

    /**
     * 是否有返回按钮（默认为true），可在具体的Activity中重写
     */
    public boolean hasBackIcon() {
        return false;
    }

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void initViews();

    public CustomDialog getDialog(CharSequence title) {
        if (dialog == null) {
            dialog = CustomDialog.instance(getActivity());
            dialog.setCancelable(true);
        }
        dialog.setTitle(title);
        return dialog;
    }

    public void hideDialog() {
        if (dialog != null)
            dialog.hide();
    }

    public void showLoadingDialog(CharSequence title) {
        getDialog(title).show();
    }

    public void showLoadingDialog(@StringRes int titleRes) {
        getDialog(getResources().getString(titleRes)).show();
    }

    public void showSysLoadingDialog(CharSequence message) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(getContext());
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.setMessage(message);
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void showSysLoadingDialog(@StringRes int resId) {
        showSysLoadingDialog(getResources().getString(resId));
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }

        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    /**
     * 跳转到下一个Activity，不销毁当前Activity
     *
     * @param cls 下一个Activity的Class
     */
    public void forward(Class<?> cls) {
        forward(cls, null, Constants.ActivityAnimType.DEFAULT);
    }

    public void forward(Class<?> cls, Bundle bundle) {
        forward(cls, bundle, Constants.ActivityAnimType.DEFAULT);
    }

    public void forward(Class<?> cls, Bundle bundle, Constants.ActivityAnimType animType) {
        Intent intent = new Intent(getContext(), cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        executeForwardAnim(animType);
    }

    /**
     * 跳转到下一个Activity，并获取返回值
     *
     * @param cls 下一个Activity的Class
     */
    public void forwardForResult(Class<?> cls, int requestCode) {
        forwardForResult(cls, null, requestCode, Constants.ActivityAnimType.DEFAULT);
    }

    public void forwardForResult(Class<?> cls, Bundle bundle, int requestCode) {
        forwardForResult(cls, bundle, requestCode, Constants.ActivityAnimType.DEFAULT);
    }

    public void forwardForResult(Class<?> cls, Bundle bundle, int requestCode, Constants.ActivityAnimType animType) {
        Intent intent = new Intent(getContext(), cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        executeForwardAnim(animType);
    }

    /**
     * 执行跳转到下一个Activity的动画
     */
    public void executeForwardAnim(Constants.ActivityAnimType animType) {
        this.animType = animType;
        switch (animType) {
            case DEFAULT:
                getActivity().overridePendingTransition(R.anim.activity_push_forward_enter, R.anim.activity_push_forward_exit);
                break;
            case DIALOG:
                getActivity().overridePendingTransition(R.anim.activity_forward_enter, R.anim.activity_forward_exit);
                break;
            case LOGIN:
                getActivity().overridePendingTransition(R.anim.activity_pop_forward_enter, R.anim.activity_pop_forward_exit);
                break;
            default:
                break;
        }
    }

    /**
     * 执行回到到上一个Activity的动画
     */
    public void executeBackwardAnim() {
        switch (this.animType) {
            case DEFAULT:
                getActivity().overridePendingTransition(R.anim.activity_push_backward_enter, R.anim.activity_push_backward_exit);
                break;
            case DIALOG:
                getActivity().overridePendingTransition(R.anim.activity_backward_enter, R.anim.activity_backward_exit);
                break;
            case LOGIN:
                getActivity().overridePendingTransition(R.anim.activity_pop_backward_enter, R.anim.activity_pop_backward_exit);
                break;
            default:
                break;
        }
    }
}
