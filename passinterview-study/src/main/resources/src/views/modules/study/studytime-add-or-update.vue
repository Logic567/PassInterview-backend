<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="题目类型id" prop="quesType">
      <el-input v-model="dataForm.quesType" placeholder="题目类型id"></el-input>
    </el-form-item>
    <el-form-item label="用户id" prop="memberId">
      <el-input v-model="dataForm.memberId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="学习时常（分）" prop="totalTime">
      <el-input v-model="dataForm.totalTime" placeholder="学习时常（分）"></el-input>
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
          quesType: '',
          memberId: '',
          totalTime: '',
          delFlag: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          quesType: [
            { required: true, message: '题目类型id不能为空', trigger: 'blur' }
          ],
          memberId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          totalTime: [
            { required: true, message: '学习时常（分）不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/study/studytime/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.quesType = data.studyTime.quesType
                this.dataForm.memberId = data.studyTime.memberId
                this.dataForm.totalTime = data.studyTime.totalTime
                this.dataForm.delFlag = data.studyTime.delFlag
                this.dataForm.createTime = data.studyTime.createTime
                this.dataForm.updateTime = data.studyTime.updateTime
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
              url: this.$http.adornUrl(`/study/studytime/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'quesType': this.dataForm.quesType,
                'memberId': this.dataForm.memberId,
                'totalTime': this.dataForm.totalTime,
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
