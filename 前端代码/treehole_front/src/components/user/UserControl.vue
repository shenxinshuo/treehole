<template>
  <div>
    <el-card>
      <div slot="header" class="clearfix">
        <span>总用户数：<strong style="color:green">{{total}}</strong></span>
      </div>
      <el-table :data="users" stripe style="width: 100%">
        <el-table-column prop="id" label="#" width="50"> </el-table-column>
        <el-table-column prop="username" label="姓名"></el-table-column>
        <!-- 新用户提醒 -->
        <el-table-column prop="isNewUser" label="是否新用户" width="100">
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.isNewUser === '是新用户' ? 'danger' : 'success'"
              disable-transitions
              >{{ scope.row.isNewUser }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="area" label="地区" width="100">
        </el-table-column>
        <el-table-column prop="age" label="年龄" width="70"> </el-table-column>
        <el-table-column prop="lock_flag" label="锁定标识"> </el-table-column>
        <el-table-column prop="telephone" label="联系方式" width="150">
        </el-table-column>
        <el-table-column prop="created_time" label="注册时间" width="200">
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="pageNum"
          :page-size="pageSize"
          layout="prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request.js";
export default {
  data() {
    return {
      users: [
        {
          id: "35",
          username: "蚝烙迈落蚝",
          isNewUser: "是",
          area: "北京",
          age: 23,
          lock_flag: 0,
          telephone: 15019717012,
          created_time: "2021-08-13 22:14:12",
        },
        {
          id: "35",
          username: "蚝烙迈落蚝",
          isNewUser: "是",
          area: "北京",
          age: 23,
          lock_flag: 0,
          telephone: 15019717012,
          created_time: "2021-08-13 22:14:12",
        },
        {
          id: "35",
          username: "蚝烙迈落蚝",
          isNewUser: "是",
          area: "北京",
          age: 23,
          lock_flag: 0,
          telephone: 15019717012,
          created_time: "2021-08-13 22:14:12",
        },
        {
          id: "35",
          username: "蚝烙迈落蚝",
          isNewUser: "是",
          area: "北京",
          age: 23,
          lock_flag: 0,
          telephone: 15019717012,
          created_time: "2021-08-13 22:14:12",
        },
      ],
      pageNum: 1,
      pageSize: 10,
      total: 0,
    };
  },
  methods: {
    handleSizeChange() {},
    handleCurrentChange() {
      request
        .get("/getUsers?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize)
        .then((response) => {
          this.users = response.data.data.list;
          for (var i = 0; i < response.data.data.endRow; i++) {
            //计算是否新用户 3天内注册的都属于新客户
            var days = parseInt(
              (new Date().getTime() -
                new Date(this.users[i].created_time).getTime()) /
                (1000 * 60 * 60 * 24)
            );
            this.users[i].isNewUser = days <= 3 ? "是新用户" : "否";
            //计算年龄
            var age =
              new Date().getFullYear() -
              new Date(this.users[i].birthday).getFullYear();
            this.users[i].age = age;
          }
        });
    },
  },
  created() {
    request
      .get("/getUsers?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize)
      .then((response) => {
        console.log(response);
        this.users = response.data.data.list;
        this.total = response.data.data.total;
        for (var i = 0; i < response.data.data.endRow; i++) {
          //计算是否新用户 3天内注册的都属于新客户
          var days = parseInt(
            (new Date().getTime() -
              new Date(this.users[i].created_time).getTime()) /
              (1000 * 60 * 60 * 24)
          );
          this.users[i].isNewUser = days <= 3 ? "是新用户" : "否";
          //计算年龄
          var age =
            new Date().getFullYear() -
            new Date(this.users[i].birthday).getFullYear();
          this.users[i].age = age;
        }
      });
  },
};
</script>

<style>
.item {
  margin-top: 10px;
  margin-right: 40px;
}
</style>