'use strict'
var gulp = require('gulp');
var nunjucksRender = require('gulp-nunjucks-render');
var webpack = require('webpack');
/*
var minify = require('gulp-minify-css');//css压缩
var concat = require('gulp-concat');//文件合并
var uglify = require('gulp-uglify');//js压缩
var imagemin = require('gulp-imagemin');//压缩图片
var htmlmin = require('gulp-htmlmin');//html压缩组件
var gulpRemoveHtml = require('gulp-remove-html');//标签清除，参考：https://www.npmjs.com/package/gulp-remove-html
var removeEmptyLines = require('gulp-remove-empty-lines');//清除空白行，参考：https://www.npmjs.com/package/gulp-remove-empty-lines
var livereload = require('gulp-livereload'); //livereload
var plumber = require('gulp-plumber');

var path = "html/pages/!*.+(html|nunjucks)";
var buildBasePath = 'html/build/';


gulp.task('nunjucks', function() {
  // Gets .html and .nunjucks files in pages
  return gulp.src('html/pages/!**!/!*.+(html|nunjucks)')
   .pipe(plumber())
  // Renders template with nunjucks
  .pipe(nunjucksRender({
      path: ['html/templates']
    }))
  .pipe(plumber())

  // output files in app folder
  .pipe(gulp.dest("html"))
  .pipe(gulp.dest("html/compile"))
});

//公共css,提取拷贝一份到发布文件夹中
gulp.task('copy',  function() {
  return gulp.src('html/lib/!*')
    .pipe(gulp.dest('html/build/lib'))
});

//压缩js
gulp.task('minifyJs', function(){
  return gulp.src('html/js/!*')
    .pipe(uglify())
    .pipe(gulp.dest('html/build/js'));
});

/!*!//压缩图片
gulp.task('minifyImg', function(){
    return gulp.src('html/images/!*')
        .pipe(imagemin())
        .pipe(gulp.dest('html/build/images'));
});*!/
//html压缩
gulp.task('html',function(){
    var options = {
        removeComments: true,//清除HTML注释
        collapseWhitespace: false,//压缩HTML
        collapseBooleanAttributes: true,//省略布尔属性的值 <input checked="true"/> ==> <input />
        removeEmptyAttributes: true,//删除所有空格作属性值 <input id="" /> ==> <input />
        removeScriptTypeAttributes: true,//删除<script>的type="text/javascript"
        removeStyleLinkTypeAttributes: true,//删除<style>和<link>的type="text/css"
        minifyJS: true,//压缩页面JS
        minifyCSS: true//压缩页面CSS
    };
    return gulp.src("html/!*.html")
        .pipe(gulpRemoveHtml())//清除特定标签
        .pipe(removeEmptyLines({removeComments: true}))//清除空白行
        .pipe(htmlmin(options))
        .pipe(gulp.dest(buildBasePath))
});
//检测文件改动 自动编译
gulp.task('watch', function () {
    gulp.watch(path, ['nunjucks'])
    gulp.watch("html/templates/!**!/!*.+(html|nunjucks)", ['nunjucks'])
    gulp.watch("html/pages/!*.+(html|nunjucks)", ['nunjucks'])
/!*    gulp.watch("html/!*.html", ['html'])   //检测编译好的未压缩HTML文件
    gulp.watch("html/js/!*.js", ['minifyJs'])*!/
   /!* .on('change', livereload.changed);
     livereload.listen();*!/
});


gulp.task('prod', ['nunjucks'/!*,'html','minifyJs','copy'*!/,'watch']);
gulp.task('default', ['prod']);*/
