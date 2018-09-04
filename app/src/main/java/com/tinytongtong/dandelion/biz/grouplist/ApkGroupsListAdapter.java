package com.tinytongtong.dandelion.biz.grouplist;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tinytongtong.dandelion.R;
import com.tinytongtong.dandelion.biz.grouplist.bean.ApkGroupsListBean;

import java.util.List;


/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2018/9/4 7:49 PM
 * @Version TODO
 */
public class ApkGroupsListAdapter extends BaseQuickAdapter<ApkGroupsListBean.DataBean.ListBean, BaseViewHolder> {
    public ApkGroupsListAdapter(int layoutResId, @Nullable List<ApkGroupsListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApkGroupsListBean.DataBean.ListBean item) {
        ImageView imageView = helper.getView(R.id.iv_icon);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvType = helper.getView(R.id.tv_type);

        // 应用的Icon图标key，访问地址为 https://www.pgyer.com/image/view/app_icons/[应用的Icon图标key]
        Glide.with(mContext).load("https://www.pgyer.com/image/view/app_icons/" + item.getBuildIcon()).into(imageView);
        tvName.setText(item.getBuildName());
        tvType.setText(getBuildType(item.getBuildType()));
    }

    private String getBuildType(int type) {
        if (1 == type) {
            return "IOS";
        } else if (2 == type) {
            return "Android";
        } else {
            return String.valueOf(type);
        }
    }
}
