<template>
  <div>
    <div v-if="questions">
      <h3>{{ total }}个问题</h3>
      <div v-for="(question, index) in questions">
        <el-divider class="fengexian1"></el-divider>
        <el-link :href="public_path + '/question/' + question.id">{{
          question.title
        }}</el-link>
        <div style="display: inline-block; float: right">
          <font color="grey">{{ question.created_time }}</font>
        </div>
        <br />
        <span style="">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-hand-thumbs-up"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16v-1c.563 0 .901-.272 1.066-.56a.865.865 0 0 0 .121-.416c0-.12-.035-.165-.04-.17l-.354-.354.353-.354c.202-.201.407-.511.505-.804.104-.312.043-.441-.005-.488l-.353-.354.353-.354c.043-.042.105-.14.154-.315.048-.167.075-.37.075-.581 0-.211-.027-.414-.075-.581-.05-.174-.111-.273-.154-.315L12.793 9l.353-.354c.353-.352.373-.713.267-1.02-.122-.35-.396-.593-.571-.652-.653-.217-1.447-.224-2.11-.164a8.907 8.907 0 0 0-1.094.171l-.014.003-.003.001a.5.5 0 0 1-.595-.643 8.34 8.34 0 0 0 .145-4.726c-.03-.111-.128-.215-.288-.255l-.262-.065c-.306-.077-.642.156-.667.518-.075 1.082-.239 2.15-.482 2.85-.174.502-.603 1.268-1.238 1.977-.637.712-1.519 1.41-2.614 1.708-.394.108-.62.396-.62.65v4.002c0 .26.22.515.553.55 1.293.137 1.936.53 2.491.868l.04.025c.27.164.495.296.776.393.277.095.63.163 1.14.163h3.5v1H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"
            />
          </svg>
          {{ getLikeCount(question.id)
          }}<span :id="'likeCount' + question.id"> </span>
        </span>
        <span :span="10" v-html="'\u00a0\u00a0\u00a0'" />
        <span>
          <i class="el-icon-view" />
          {{ question.lookCount }}
        </span>
      </div>
      <el-divider class="fengexian1"></el-divider>
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
    </div>
    <div v-else>您还未提出过问题</div>
  </div>
</template>

<script>
import request from "@/utils/request.js";
export default {
  data() {
    return {
      currentUserID: "",
      pageNum: 1,
      pageSize: 10,
      questions: [],
      total: 0,
      public_path: process.env.VUE_APP_PUBLIC_PATH,
    };
  },
  methods: {
    handleSizeChange() {},
    handleCurrentChange() {
      request
        .get(
          "/getQuestionsByUID/" +
            this.currentUserID +
            "?pageNum=" +
            this.pageNum +
            "&pageSize=" +
            this.pageSize
        )
        .then((response) => {
          //console.log(response);
          this.questions = response.data.data.list;
          this.total = response.data.data.total;
        });
    },
    async postMessage(qid) {
      // return new Promise(resolve, reject) => {
      // 	request.get("/getLikeCountOfQuestion/"+qid).then(response => {
      // 		resolve(response.data.data)
      // 	})
      // }
      let obj = await request.get("/getLikeCountOfQuestion/" + qid);
      return obj.data.data;
    },
  },
  computed: {
    getLikeCount: function () {
      //使用computed计算属性传参，闭包
      // return function(qid) {
      // 	request.get("/getLikeCountOfQuestion/"+qid)
      // 	return qid
      // }
      return function (qid) {
        let obj = this.postMessage(qid);
        let temp = 0;
        obj.then((result) => {
          document.getElementById("likeCount" + qid).innerText = result;
        });
      };
    },
  },
  mounted() {
    function getCookie(name) {
      var arr,
        reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
      if ((arr = document.cookie.match(reg))) return unescape(arr[2]);
      else return null;
    }
    this.currentUserID = getCookie("currentUserID");
    request
      .get(
        "/getQuestionsByUID/" +
          this.currentUserID +
          "?pageNum=" +
          this.pageNum +
          "&pageSize=" +
          this.pageSize
      )
      .then((response) => {
        //console.log(response);
        this.questions = response.data.data.list;
        this.total = response.data.data.total;
      });
  },
};
</script>

<style>
h3 {
  margin-top: 0px;
  margin-bottom: 0px;
}
.fengexian1 {
  margin-top: 5px;
  margin-bottom: 10px;
}
</style>
