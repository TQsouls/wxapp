package com.wxapp.responseentity.other;

import com.wxapp.dbbean.TbUserAccountEntity;

import java.io.Serializable;
import java.util.List;

/**
 *  * -"data": {返回的数据（登录成功就返回该账号的数据，失败则不用返回）<object>
 *     * -"success": [...<array>
 *                  * -{
 *                      * "account": "",... <string>
 *                      * "account_pwd": "",... <string>
 *                      * "account_62_a16": "",... <string>
 *                      * "tag_name": "",... <string>
 *                      * "tag_id": "",... <string>
 *                      * "account_isValid": "",... <string>
 *                      * "account_state": "",... <string>
 *                      * "account_friendCount": "",... <string>
 *                      * "account_wxid": "",... <string>
 *                      * "group_name": "",... <string>
 *                      * "group_id": "",... <string>
 *                      * "user_id": ""... <string>
 *                  * }
 *              * ],
 *          * -"error": [...<array>
 *          * "1003"
 *          * ]
 *  * },
 */
public class ResponseUserData implements Serializable {
    private List<TbUserAccountEntity> success;
    private List<TbUserAccountEntity> error;

    public ResponseUserData( ) {
    }

    public ResponseUserData(List<TbUserAccountEntity> success, List<TbUserAccountEntity> error) {
        this.success = success;
        this.error = error;
    }

    public List<TbUserAccountEntity> getSuccess() {
        return success;
    }

    public void setSuccess(List<TbUserAccountEntity> success) {
        this.success = success;
    }

    public List<TbUserAccountEntity> getError() {
        return error;
    }

    public void setError(List<TbUserAccountEntity> error) {
        this.error = error;
    }
}
