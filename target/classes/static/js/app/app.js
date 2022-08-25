new Vue({
    el: '#main-container',
    data: {
        pageInfo: {
            current: 1,
            size: 5
        },
        app: {
            platform: 0,
            forceUpdate: 0
        },
        active: false
    },
    methods: {
        select: function (pageNum, pageSize) {
            axios({
                url: '/manager/app/select',
                params: {
                    current: pageNum,
                    size: pageSize,
                }
            }).then(response => {
                //箭头函数会将上下文中的this(vue对象)传递过来
                //response.data.data:pageInfo
                this.pageInfo = response.data.data;  //response.data:ResultBean
            }).catch(function (error) {
                console.log(error.message);
            });
        },
        save: function () {
            axios({
                url: '/manager/app/saveOrUpdate',
                method: 'POST',
                data: this.app,
            }).then(response => {
                if (response.data.code == 200) {
                    //更新表格
                    this.select(this.pageInfo.current, this.pageInfo.size);
                    this.app = {
                        platform: 0,
                        forceUpdate: 0
                    }
                }
                //弹提示消息
                layer.msg(response.data.msg);
            });
        },
        toUpdate: function (app_id) {
            axios({
                url: '/manager/app/selectOne',
                params: {id: app_id}
            }).then(response => {
                if (response.data.code != 200) {
                    layer.msg(response.data.msg);
                    return;
                }
                //弹消息框
                layer.appVersion = response.data.data;
                //打开layer的所在的页面
                let index = layer.open({
                    type: 2,
                    title: '更新app',
                    content: '/manager/app/app-update.html',
                    area: ['60%', '80%'],
                    end: () => {
                        this.select(this.pageInfo.current, this.pageInfo.size);
                    }
                });
            });
        },
        //通过id删除
        doDelete: function (appid) {
            layer.msg('是否删除？', {
                time: 0,  //无自动消失计时
                btn: ['是', '否'],
                yes: index => {  // index是当前消息框的索引
                    axios({
                        url: '/manager/app/doDelete',
                        params: {
                            id: appid
                        }
                    }).then(result => {
                        layer.close(index);  //关闭当前msg窗口
                        layer.msg(result.data.msg);
                        //成功后刷新数据列表
                        if (result.data.code == 200) {
                            this.select(this.pageInfo.current, this.pageInfo.size);
                        }
                    });
                }
            });
        }
    },
    created: function () {
        this.select(this.pageInfo.current, this.pageInfo.size);
    }
});