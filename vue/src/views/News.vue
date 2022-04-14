<template>
  <div style="padding: 10px">
<!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="addUser">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
<!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="searche" placeholder="请输入关键字" clearable style="width: 20%;height: 100%"/>
      <el-button type="primary" style="margin-left: 5px;height: 100%" @click="load">查询</el-button>
    </div>
<!--    内容区域-->
    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="nickName" label="昵称"/>
      <el-table-column prop="sex" label="性别"/>
      <el-table-column prop="age" label="年龄"/>
      <el-table-column prop="address" label="地址" />
      <el-table-column prop="register" label="注册时间" />
      <el-table-column label="头像" >
        <template #default="scope">
          <el-avatar shape="square" :size="100" :fit="[scope.row.photo]" :src="scope.row.photo" />
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除吗?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button size="small" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination
          v-model:currentPage="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"/>
      <!--添加用户时,弹窗-->
      <el-dialog v-model="dialogTableVisible" title="提示" width="30%">
        <el-form :model="form" label-width="120px">

          <el-form-item label="用户名">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickName"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio label="男" v-model="form.sex">男</el-radio>
            <el-radio label="女" v-model="form.sex">女</el-radio>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address"></el-input>
          </el-form-item>
          <el-form-item label="头像">
            <el-upload ref="upload" action="http://localhost:9090/files/upload" :on-success="fileUploadSuccess">
              <el-button type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
        </el-form>

        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogTableVisible = false">取消</el-button>
            <el-button type="primary" @click="saveUser">确定</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'UserView',
  components: {

  },
  data(){
    return {
      form: {},
      dialogTableVisible: false,
      currentPage: 1,
      pageSize: 5,
      total: 0,
      searche: '',
      tableData: [
      ],
    }
  },
  /*页面加载的时候触发*/
  created() {
    this.load()
  },
  //
  methods: {
    fileUploadSuccess(res){
      console.log("图片上传路径详细地址"+res.data)
      this.form.photo=res.data
    },
    load(){
      request.get("/user/selectUser",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.searche
        }
      }).then(res => {
        this.tableData=res.data.list
        this.total=res.data.total
      })
    },
    handleEdit(row){
      this.form=JSON.parse(JSON.stringify(row))
      this.dialogTableVisible=true
      /*设置懒加载****/
      this.$nextTick(()=>{
        /* 清除 已上传的 文件记录 */
        this.$refs['upload'].clearFiles()
      })

    },
    handleDelete(id){
      request.delete("/user/deleteUser/"+id).then(res=>{
        console.log(res);
        if(res.code === "0"){
          this.$message({
            type: "success",
            message: "删除成功"
          })
          this.load()
        }else{
          this.$message({
            type: "error",
            message: "删除失败"
          })
        }
      })
    },
    handleSizeChange(){   //改变每页条数
      this.load()
    },
    handleCurrentChange(){  //改变当前页码
      this.load()
    },
    addUser(){
      this.form={}
      this.dialogTableVisible=true
      this.$refs['upload'].clearFiles()  /*清除文件上传之后的历史记录*/
    },
    saveUser(){
      if(this.form.id){  //如果id有数据的话,则是更新
        request.put("/user/updateUser",this.form).then(res =>{
          console.log(res);
          if(res.code === "0"){
            this.$message({
              type: "success",
              message: "更新成功"
            })
          }else{
            this.$message({
              type: "error",
              message: "更新失败"
            })
          }
        })
      }else{  //如果无数据的话,则是新增
        request.post("/user/saveUser",this.form).then(res =>{
          console.log(res);
          if(res.code === "0"){
            this.$message({
              type: "success",
              message: "新增成功"
            })
          }else{
            this.$message({
              type: "error",
              message: "新增失败"
            })
          }
        })
      }
      this.load()
      this.dialogTableVisible=false
    }
  }
}
</script>
