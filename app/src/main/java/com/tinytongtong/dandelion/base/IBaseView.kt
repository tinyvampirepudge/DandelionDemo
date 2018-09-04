package com.tinytongtong.dandelion.base

/**
 * @Description: $desc$
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/9/4 3:15 PM
 */
interface IBaseView {
    fun showLoadingDialog()
    fun dismissLoadingDialog()
    fun showErrorMsg(errorMsg: String)
}