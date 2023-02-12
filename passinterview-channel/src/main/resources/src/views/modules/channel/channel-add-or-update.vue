<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="渠道名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="渠道名称"></el-input>
    </el-form-item>
    <el-form-item label="渠道appid" prop="appid">
      <el-input v-model="dataForm.appid" placeholder="渠道appid"></el-input>
    </el-form-item>
    <el-form-item label="渠道appsecret" prop="appsecret">
      <el-input v-model="dataForm.appsecret" placeholder="渠道appsecret"></el-input>
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
          name: '',
          appid: '',
          appsecret: '',
          delFlag: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          name: [
            { required: true, message: '渠道名称不能为空', trigger: 'blur' }
          ],
          appid: [
            { required: true, message: '渠道appid不能为空', trigger: 'blur' }
          ],
          appsecret: [
            { required: true, message: '渠道appsecret不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/channel/channel/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.channel.name
                this.dataForm.appid = data.channel.appid
                this.dataForm.appsecret = data.channel.appsecret
                this.dataForm.delFlag = data.channel.delFlag
                this.dataForm.createTime = data.channel.createTime
                this.dataForm.updateTime = data.channel.updateTime
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
              url: this.$http.adornUrl(`/channel/channel/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'appid': this.dataForm.appid,
                'appsecret': this.dataForm.appsecret,
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
