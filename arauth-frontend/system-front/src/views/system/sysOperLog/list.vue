<template>
  <div class="app-container"> 操作日志
    <div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="24">
            <el-form-item label="操作模块">
              <el-input v-model="searchObj.title" style="width: 100%" placeholder="操作模块" />
            </el-form-item>
            <el-form-item label="操作人">
              <el-input v-model="searchObj.operName" style="width: 100%" placeholder="操作人" />
            </el-form-item>
          </el-col>
          <el-col :span="24">

          </el-col>
        </el-row>
        <el-row style="display:flex">
          <el-button type="primary" icon="el-icon-search" size="mini" @click="fetchData()">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetData">重置</el-button>
        </el-row>
      </el-form>
    </div>
    <!-- 表格 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      border
      style="width: 100%;margin-top: 10px;"
    >

      <el-table-column
        label="序号"
        width="70"
        align="center"
      >
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="title" label="系统模块" />
      <el-table-column prop="businessType" label="操作类型" />
      <el-table-column prop="requestMethod" label="请求方式" />
      <el-table-column prop="operName" label="操作人员" />
      <el-table-column prop="operIp" label="操作IP" />
      <el-table-column prop="method" label="请求方法" />
      <el-table-column prop="status" label="操作状态" />
      <el-table-column prop="createTime" label="创建时间" width="160" />
      <el-table-column prop="" label="操作" />
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="fetchData"
    />
  </div>
</template>

<script>
import api from '@/api/system/sysOperLog'

const defaultForm = {
  id: '',
  roleName: '',
  roleCode: ''
}

export default {
  data() {
    return {
      listLoading: true, // 数据是否正在加载
      list: [], // 列表
      total: 0, // 总记录数
      page: 1, // 页码
      limit: 5, // 每页记录数
      searchObj: {}, // 查询条件

      dialogVisible: false,
      sysRole: defaultForm,
      saveBtnDisabled: false,

      multipleSelection: [] // 批量删除选中的记录列表
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData(pageNum = 1) {
      this.page = pageNum
      // 调用api
      api.getPageList(this.page, this.limit, this.searchObj).then(response => {
        // debugger
        this.listLoading = false
        this.list = response.data.records
        this.total = response.data.total
      })
    },
    // 重置表单
    resetData() {
      console.log('重置查询表单')
      this.searchObj = {}
      this.fetchData()
    }
  }
}
</script>

