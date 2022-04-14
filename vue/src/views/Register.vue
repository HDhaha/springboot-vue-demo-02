<template>
  <div style="width: 100%;height: 100vh;background-color: darkslateblue;overflow: hidden">
      <div style="width: 400px;margin: 150px auto">
        <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px 0">欢迎注册</div>
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
              <!--密码隐藏属性 show-password -->
              <el-input v-model="form.newpass" placeholder="请再次输入密码" show-password>
                <template #prefix>
                  <el-icon class="el-input__icon"><lock /></el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" style="width: 100%" @click="register">注 册</el-button>
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
  name: "Register",
  components: {
    Avatar,
    Lock
  },
  data(){
    return{
      form: {},
      /*设置登陆校验,*/
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
    register(){  /*点击注册时触发*/
      if(this.form.password !== this.form.newpass){
        this.$message({
          type: "error",
          message: "两次密码不一致"
        })
        return
      }

    this.$refs['form'].validate((valid) => {
        if(valid){  /*当登陆校验通过valid才会 ==true*/
          request.post("/account/register",this.form).then(res =>{
            if(res.code === "0"){
              this.$message({
                type: "success",
                message: "注册成功"
              })
              this.$router.push("/user")    /*vue  页面重定向,登陆成功登陆主页*/
            }else {
              this.$message({
                type: "error",
                message: "注册失败,该用户名已经注册"
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