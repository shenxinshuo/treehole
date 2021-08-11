<template>
  <div ref="editor"></div>
</template>

<script>
import E from 'wangeditor';
export default {
  props: {
    value: {
      type: String,
      default: '',
    },
    meanArray: {
      // 自定义菜单
      type: Array,
      default: null,
    },
  },
  model: {
    prop: 'value',
    event: 'change',
  },
  watch: {
    value: function (value) {
      if (value !== this.editor.txt.html()) {
        this.editor.txt.html(this.value);
      }
    },
    //value为编辑框输入的内容，这里我监听了一下值，当父组件调用得时候，如果给value赋值了，子组件将会显示父组件赋给的值
  },
  data() {
    return {
      // 默认有这么多菜单，meanArray有值以meanArray为准
      defaultMeanus: [
        'head',
        'bold',
        'fontSize',
        'fontName',
        'italic',
        'underline',
        'strikeThrough',
        'indent',
        'lineHeight',
        'foreColor',
        'backColor',
        'link',
        'list',
        'justify',
        'quote',
        'emoticon',
        'image',
        'video',
        'table',
        'code',
        'splitLine',
        'undo',
        'redo',
      ],
      editor: '',
    };
  },
  methods: {
    init() {
      const _this = this;
      this.editor = new E(this.$refs.editor);
      this.editor.config.uploadImgShowBase64 = true; // 使用 base64 保存图片
      this.setMenus(); //设置菜单
	  this.editor.config.zindex=20000;
      this.editor.config.onchange = (html) => {
        _this.$emit('change', html); // 将内容同步到父组件中
      };
      this.editor.create(); //创建编辑器
    },
    setMenus() {
      // 设置菜单
      if (this.meanArray) {
        this.editor.config.menus = this.meanArray;
      } else {
        this.editor.config.menus = this.defaultMeanus;
      }
    },
    getHtml() {
      // 得到文本内容
      return this.editor.txt.html();
    },
    setHtml(txt) {
      // 设置富文本里面的值
      this.editor.txt.html(txt);
    },
  },
  mounted() {
    let that = this;
    that.$nextTick(function () {
      that.init();
    });
  },
};
</script>
<style>
	.w-e-menu{
	    z-index: 2 !important;
	}
	.w-e-text-container{
	    z-index: 1 !important;
	}
	.w-e-toolbar {
		z-index: 2 !important;
	}
	.w-e-droplist {
		z-index: 3 !important;
	}
</style>
