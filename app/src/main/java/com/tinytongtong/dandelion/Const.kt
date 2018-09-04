package com.tinytongtong.dandelion

/**
 * @Description: https://www.pgyer.com/doc/view/api#listMyReleased
 *
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/9/4 2:12 PM
 */
/**
 * API Key，用来识别API调用者的身份，如不特别说明，每个接口中都需要含有此参数。对于同一个蒲公英的注册用户来说，这个值在固定的。
 */
val _api_key = "_api_key"
val _api_key_value = "0ab7be11c24adad65c39ac751c92858b"

//	用户Key，用来标识当前用户的身份，对于同一个蒲公英的注册用户来说，这个值在固定的。
val userKey = "userKey"
val userKeyValue = "a461dd3b073fb6f0ba0f12e8a7a15838"

//	表示一个App组的唯一Key。例如，名称为'微信'的App上传了三个版本，那么这三个版本为一个App组，该参数表示这个组的Key。这个值显示在应用详情--应用概述--App Key。
val appKey = "appKey"

//Build Key是唯一标识应用的索引ID，可以通过 获取App所有版本取得
val buildKey = "buildKey"