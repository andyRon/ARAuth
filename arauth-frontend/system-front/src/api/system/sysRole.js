import request from '@/utils/request'

const api_name = '/admin/system/sysRole'

export default {
    getPageList(page, limit, searchObj) {
        return request({
            url: `${api_name}/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },
    removeById(id) {
        return request({
          url: `${api_name}/remove/${id}`,
          method: 'delete'
        })
    },
    save(role) {
        return request({
          url: `${api_name}/save`,
          method: 'post',
          data: role
        })
    },
    //编辑
    getById(id) {
        return request({
        url: `${api_name}/findRoleById/${id}`,
        method: 'get'
        })
    },
    //更新
    updateById(role) {
        return request({
        url: `${api_name}/update`,
        method: 'put',
        data: role
        })
    },
    batchRemove(idList) {
        return request({
          url: `${api_name}/batchRemove`,
          method: `delete`,
          data: idList
        })
    },
    //根据用户id查询用户已分配的角色
    getRolesByUserId(userId) {
        return request({
            url: `${api_name}/toAssign/${userId}`,
            method: 'get'
        })
    },
    //分配角色
    assignRoles(assginRoleVo) {
        return request({
            url: `${api_name}/doAssign`,
            method: 'post',
            data: assginRoleVo
        })
  }
}