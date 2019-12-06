function updateArticle() {
    var title=$('#title').val();
    var content=$('#content').val();
    var date=$('#date').val();
    var image=$('#image').val();
    var category=$('#category').val();
    var id=$('#id').val();

    alert(title);
    $.ajax({
        url: '/admin/updateArticle',
        type: 'post',
        // 设置的是请求参数
        data: {title:title,content:content,pushdate:date,image:image,category:category,id:id},
        // 用于设置响应体的类型 注意 跟 data 参数没关系！！！
        dataType: 'json',
        success: function (res) {
            // 一旦设置的 dataType 选项，就不再关心 服务端 响应的 Content-Type 了
            // 客户端会主观认为服务端返回的就是 JSON 格式的字符串
            console.log(res);
            alert(res);
        },
        error: function (xhr) {
            // 隐藏 loading
            // 只有请求不正常（状态码不为200）才会执行
            console.log('error', xhr);
        }
    })
}