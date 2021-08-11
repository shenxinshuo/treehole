<template>
    <div>
        <div ref="editor" style="text-align:left" class="textBox">
          <p>嘴上说着爱你，心里骂你傻逼</p>
        </div>
        <button v-on:click="getContent">查看内容</button>
    </div>
</template>

<script>
    import E from 'wangeditor'
    export default {
      data () {
        return {
          editorContent: '',
          editor:null
        }
      },
      methods: {
        getContent: function () {
            // alert(this.editorContent)
          var json = this.editor.txt.getJSON()  // 获取 JSON 格式的内容
          var jsonStr = JSON.stringify(json)
          console.log(json)
          console.log(jsonStr)
          console.log(this.editorContent,'----------------------')
        }
      },
      mounted() {
        this.editor = new E(this.$refs.editor)
        
        this.editor.create()
      }
    }
</script>

<style scoped>
</style>
