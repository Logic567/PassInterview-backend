<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="access_token" prop="accessToken">
      <el-input v-model="dataForm.accessToken" placeholder="access_token"></el-input>
    </el-form-item>
    <el-form-item label="到期时间" prop="expireTime">
      <el-input v-model="dataForm.expireTime" placeholder="到期时间"></el-input>
    </el-form-item>
    <el-form-item label="渠道id" prop="channelId">
      <el-input v-model="dataForm.channelId" placeholder="渠道id"></el-input>
    </el-form-item>
    <el-form-item label="删除标记（0-正常，1-删除）" prop="delFlag">
      <el-input v-model="dataForm.delFlag" placeholder="删除标记（0-正常，1-删除）"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          accessToken: '',
          expireTime: '',
          channelId: '',
          delFlag: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          accessToken: [
            { required: true, message: 'access_token不能为空', trigger: 'blur' }
          ],
          expireTime: [
            { required: true, message: '到期时间不能为空', trigger: 'blur' }
          ],
          channelId: [
            { required: true, message: '渠道id不能为空', trigger: 'blur' }
          ],
          delFlag: [
            { required: true, message: '删除标记（0-正常，1-删除）不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/channel/accesstoken/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.accessToken = data.accessToken.accessToken
                this.dataForm.expireTime = data.accessToken.expireTime
                this.dataForm.channelId = data.accessToken.channelId
                this.dataForm.delFlag = data.accessToken.delFlag
                this.dataForm.createTime = data.accessToken.createTime
                this.dataForm.updateTime = data.accessToken.updateTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/channel/accesstoken/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'accessToken': this.dataForm.accessToken,
                'expireTime': this.dataForm.expireTime,
                'channelId': this.dataForm.channelId,
                'delFlag': this.dataForm.delFlag,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
