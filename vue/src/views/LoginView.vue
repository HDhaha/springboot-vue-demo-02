<template>
  <div style="width: 100%;height: 100vh;background-color: darkslateblue;overflow: hidden">
      <div style="width: 400px;margin: 150px auto">
        <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px 0">欢迎登陆</div>
        <div>
          <el-form ref="form" :model="form" style="width: 80%;margin-left: 10%" size="nomal" :rules="reactive">
            <el-form-item prop="username">
              <el-input v-model="form.username" placeholder="请输入用户名">
                <template #prefix>
                  <el-icon class="el-input__icon"><avatar /></el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <!--密码隐藏属性 show-password -->
              <el-input v-model="form.password" placeholder="请输入密码" show-password>
                <template #prefix>
                  <el-icon class="el-input__icon"><lock /></el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" style="width: 100%" @click="login">登 陆</el-button>
            </el-form-item>
          </el-form>
        </div>

      </div>
  </div>
</template>

<script>
import { Avatar,Lock } from '@element-plus/icons'
import request from "@/utils/request";
export default {
  name: "LoginView",
  components: {
    Avatar,
    Lock
  },
  data() {
    return {
      form: {},
      reactive: ({
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          // {min: 1, max: 5, message: '密码长度在1--5', trigger: 'blur'},
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      }),
    }
  },
  methods:{
    login(){
      this.$refs['form'].validate((valid) => {
        if (valid) {  /*当登陆校验通过valid才会 ==true*/
          request.post("/account/login",this.form).then(res =>{
            if(res.code === "0"){
              this.$message({
                type: "success",
                message: "登陆成功"
              })
              sessionStorage.setItem("user",JSON.stringify(res.data))
              this.$router.push("/user")    /*vue  页面重定向,登陆成功登陆主页*/
            }else {
              this.$message({
                type: "error",
                message: "用户名或者密码错误"
              })
            }
          })
        }
      })
    }
  }

}
</script>

<style scoped>

</style>