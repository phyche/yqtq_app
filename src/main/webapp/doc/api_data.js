define({ "api": [
  {
    "type": "post",
    "url": "/api/hostRace/apply",
    "title": "草根杯报名",
    "name": "hostRace_apply",
    "group": "hostRace",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "raceId",
            "description": "<p>草根杯id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "eventInformation",
            "description": "<p>草根杯赛事资讯列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "eventInformation.content",
            "description": "<p>草根杯赛事资讯内容</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/HostRaceApi.java",
    "groupTitle": "hostRace",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/hostRace/apply"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/hostRace/eventInformation",
    "title": "草根杯赛事资讯",
    "name": "hostRace_eventInformation",
    "group": "hostRace",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "raceId",
            "description": "<p>草根杯id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "eventInformation",
            "description": "<p>草根杯赛事资讯列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "eventInformation.content",
            "description": "<p>草根杯赛事资讯内容</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/HostRaceApi.java",
    "groupTitle": "hostRace",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/hostRace/eventInformation"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/hostRace/list",
    "title": "草根杯列表",
    "name": "hostRace_list",
    "group": "hostRace",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "hostRace",
            "description": "<p>草根杯</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "hostRace.id",
            "description": "<p>草根杯id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "hostRace.type",
            "description": "<p>草根杯赛制</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "hostRace.name",
            "description": "<p>草根杯名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "hostRace.avater",
            "description": "<p>草根杯封面</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "hostRace.createDate",
            "description": "<p>草根杯创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "hostRace.stadium",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "hostRace.stadium.name",
            "description": "<p>球场名称</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/HostRaceApi.java",
    "groupTitle": "hostRace",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/hostRace/list"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/hostRace/teamList",
    "title": "草根杯参赛队伍",
    "name": "hostRace_teamList",
    "group": "hostRace",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "raceId",
            "description": "<p>草根杯id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>草根杯列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.team",
            "description": "<p>草根杯参赛队伍列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.team.id",
            "description": "<p>球队id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.team.name",
            "description": "<p>球队名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.team.avater",
            "description": "<p>球队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.team.count",
            "description": "<p>球队总人数</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.team.address",
            "description": "<p>球队所在地</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.team.num",
            "description": "<p>球队场次</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/HostRaceApi.java",
    "groupTitle": "hostRace",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/hostRace/teamList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/interact/addFriend",
    "title": "添加好友",
    "name": "interact_addFriend",
    "group": "interact",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>好友手机号（账号） &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/InteractApi.java",
    "groupTitle": "interact",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/interact/addFriend"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/interact/addressBook",
    "title": "通讯录",
    "name": "interact_addressBook",
    "group": "interact",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>通讯录列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.id",
            "description": "<p>通讯录id</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.toUser",
            "description": "<p>通讯录好友</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.toUser.id",
            "description": "<p>好友id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.toUser.nickname",
            "description": "<p>好友昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.toUser.avater",
            "description": "<p>好友头像</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/InteractApi.java",
    "groupTitle": "interact",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/interact/addressBook"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/interact/comment",
    "title": "评论回复",
    "name": "interact_comment",
    "group": "interact",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "postId",
            "description": "<p>足球圈id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>评论人id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "touserId",
            "description": "<p>被评论人id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>内容</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/InteractApi.java",
    "groupTitle": "interact",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/interact/comment"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/interact/commentList",
    "title": "评论列表",
    "name": "interact_commentList",
    "group": "interact",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "postId",
            "description": "<p>足球圈id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>足球圈评论列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.fUser",
            "description": "<p>评论人</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.fUser.id",
            "description": "<p>评论人id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.fUser.nickname",
            "description": "<p>评论人昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.fUser.avater",
            "description": "<p>评论人头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.tUser",
            "description": "<p>评论人</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.tUser.id",
            "description": "<p>被评论人id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.tUser.nickname",
            "description": "<p>被评论人昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.tUser.avater",
            "description": "<p>被评论人头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.content",
            "description": "<p>评论内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.createDate",
            "description": "<p>评论时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/InteractApi.java",
    "groupTitle": "interact",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/interact/commentList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/interact/listActivity",
    "title": "活动列表",
    "name": "interact_listActivity",
    "group": "interact",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNum",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>活动列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.id",
            "description": "<p>活动id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.title",
            "description": "<p>活动标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.avater",
            "description": "<p>活动封面</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.introduction",
            "description": "<p>活动简介</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.description",
            "description": "<p>活动介绍</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.createDate",
            "description": "<p>活动创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "page",
            "description": "<p>翻页信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalNum",
            "description": "<p>总记录数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.currentPage",
            "description": "<p>当前页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/InteractApi.java",
    "groupTitle": "interact",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/interact/listActivity"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/interact/listInformation",
    "title": "资讯列表",
    "name": "interact_listInformation",
    "group": "interact",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNum",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>资讯列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.Information.id",
            "description": "<p>资讯id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.title",
            "description": "<p>资讯标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.avater",
            "description": "<p>资讯封面</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.introduction",
            "description": "<p>资讯简介</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.description",
            "description": "<p>资讯介绍</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.createDate",
            "description": "<p>资讯创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "page",
            "description": "<p>翻页信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalNum",
            "description": "<p>总记录数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.currentPage",
            "description": "<p>当前页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/InteractApi.java",
    "groupTitle": "interact",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/interact/listInformation"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/interact/listPost",
    "title": "足球圈列表",
    "name": "interact_listPost",
    "group": "interact",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNum",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>足球圈列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.content",
            "description": "<p>足球圈内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.shareNum",
            "description": "<p>分享数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.commentNum",
            "description": "<p>评论数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.user",
            "description": "<p>发足球圈用户</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.user.id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.user.nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.user.avater",
            "description": "<p>用户头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.postImages",
            "description": "<p>足球圈图片列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.postImages.avater",
            "description": "<p>足球圈图片</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.postCommentList",
            "description": "<p>足球圈评论列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.postCommentList.fUser",
            "description": "<p>评论人</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.postCommentList.fUser.id",
            "description": "<p>评论人id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.postCommentList.fUser.nickname",
            "description": "<p>评论人昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.postCommentList.fUser.avater",
            "description": "<p>评论人头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.postCommentList.tUser",
            "description": "<p>被评论人</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.postCommentList.tUser.id",
            "description": "<p>被评论人id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.postCommentList.tUser.nickname",
            "description": "<p>被评论人昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.postCommentList.tUser.avater",
            "description": "<p>被评论人头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.postCommentList.content",
            "description": "<p>评论内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.postCommentList.createDate",
            "description": "<p>评论时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "page",
            "description": "<p>翻页信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalNum",
            "description": "<p>总记录数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.currentPage",
            "description": "<p>当前页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/InteractApi.java",
    "groupTitle": "interact",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/interact/listPost"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/interact/publish",
    "title": "发布圈子",
    "name": "interact_publish",
    "group": "interact",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>内容 &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Object",
            "optional": false,
            "field": "imagesMap",
            "description": "<p>图片数组Map</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/InteractApi.java",
    "groupTitle": "interact",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/interact/publish"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/login/checkLogin",
    "title": "登录检查",
    "name": "login_checkLogin",
    "group": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号  &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码  &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "user",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "user.id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "user.mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "user.password",
            "description": "<p>密码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "user.nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.age",
            "description": "<p>年龄</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "user.height",
            "description": "<p>身高</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "user.weight",
            "description": "<p>体重</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.position",
            "description": "<p>位置（0：前 1：中 2：后 3：守）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.credibility",
            "description": "<p>信誉度</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.vipNum",
            "description": "<p>vip等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.integral",
            "description": "<p>积分</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.experience",
            "description": "<p>经验值</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "user.avater",
            "description": "<p>头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "user.endDate",
            "description": "<p>会员截止日期</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.status",
            "description": "<p>状态 （0：男 1：女）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.gender",
            "description": "<p>性别（0：正常 1：冻结）</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "user.birthday",
            "description": "<p>出生年月</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "user.provinceId",
            "description": "<p>省份</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "user.cityId",
            "description": "<p>城市</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "user.teamId",
            "description": "<p>我的球队id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/LoginApi.java",
    "groupTitle": "login",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/login/checkLogin"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/login/confirmCode",
    "title": "确认验证码",
    "name": "login_confirmCode",
    "group": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号  &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "requestCode",
            "description": "<p>验证码  &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/LoginApi.java",
    "groupTitle": "login",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/login/confirmCode"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/login/findPassword",
    "title": "设置密码",
    "name": "login_findPassword",
    "group": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号  &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>登录密码  &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/LoginApi.java",
    "groupTitle": "login",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/login/findPassword"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/login/sendCode",
    "title": "发送验证码",
    "name": "login_sendCode",
    "group": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号  &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "codeMap",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "codeMap.mobile",
            "description": "<p>发送验证码的手机号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "codeMap.code",
            "description": "<p>验证码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/LoginApi.java",
    "groupTitle": "login",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/login/sendCode"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/login/tLogin",
    "title": "第三方登录",
    "name": "login_tLogin",
    "group": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "type",
            "description": "<p>第三方类型，1=微信，2=QQ，3=新浪微博       &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "openId",
            "description": "<p>唯一标识       &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "head",
            "description": "<p>头像路径       &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>昵称       &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "userInfo",
            "description": "<p>用户信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.nickname",
            "description": "<p>昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.avater",
            "description": "<p>头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.credibility",
            "description": "<p>信誉度</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.createTime",
            "description": "<p>注册时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.cityId",
            "description": "<p>所在城市id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/LoginApi.java",
    "groupTitle": "login",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/login/tLogin"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/message/doMessage",
    "title": "处理消息",
    "name": "message_doMessage",
    "group": "message",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "id",
            "description": "<p>消息id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "status",
            "description": "<p>状态（1：同意，2：拒绝） &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "type",
            "description": "<p>类型（1：处理约球消息，2：处理添加好友消息，3：处理加入球队消息，4：处理球队约战消息） &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/MessageApi.java",
    "groupTitle": "message",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/message/doMessage"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/message/messageStatus",
    "title": "消息状态",
    "name": "message_messageStatus",
    "group": "message",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>消息id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "status",
            "description": "<p>消息状态(0：有新消息，1：没有)</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/MessageApi.java",
    "groupTitle": "message",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/message/messageStatus"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/message/orderBall",
    "title": "好友约球消息",
    "name": "message_orderBall",
    "group": "message",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>消息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>消息id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.type",
            "description": "<p>消息类型（4：好友约球消息，5：好友加入约球消息，6：约球成功或失败消息）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.content",
            "description": "<p>消息内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.createDate",
            "description": "<p>消息时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.startTime",
            "description": "<p>约球开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.userId",
            "description": "<p>好友id (好友请求)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.nickname",
            "description": "<p>好友昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadiumname",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.status",
            "description": "<p>约球状态（1：成功，2：失败）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.reserveType",
            "description": "<p>约球类型（0：私人，1：公共）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.matchType",
            "description": "<p>约球赛制（N赛制）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.joinCount",
            "description": "<p>约球参加人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.lackNum",
            "description": "<p>约球缺的人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.reserveId",
            "description": "<p>约球id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/MessageApi.java",
    "groupTitle": "message",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/message/orderBall"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/message/post",
    "title": "帖子消息",
    "name": "message_post",
    "group": "message",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>帖子消息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>消息id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.title",
            "description": "<p>消息标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.content",
            "description": "<p>消息评论内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.createDate",
            "description": "<p>消息评论时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.fUser",
            "description": "<p>好友</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.fUser.id",
            "description": "<p>好友id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.fUser.nickname",
            "description": "<p>好友昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.post",
            "description": "<p>帖子</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.post.id",
            "description": "<p>帖子id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.post.content",
            "description": "<p>帖子内容</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/MessageApi.java",
    "groupTitle": "message",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/message/post"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/message/system",
    "title": "系统消息",
    "name": "message_system",
    "group": "message",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>消息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>消息id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.type",
            "description": "<p>消息类型（1：系统消息，2：添加好友消息，3：好友请求消息）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.content",
            "description": "<p>消息内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.createDate",
            "description": "<p>消息时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.toUserId",
            "description": "<p>好友id (添加好友)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.nickname",
            "description": "<p>好友昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.userId",
            "description": "<p>好友id (好友请求)</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/MessageApi.java",
    "groupTitle": "message",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/message/system"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/message/systemInfo",
    "title": "系统消息详情",
    "name": "message_systemInfo",
    "group": "message",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "systemId",
            "description": "<p>系统消息id &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "message",
            "description": "<p>系统消息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "message.id",
            "description": "<p>消息id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message.title",
            "description": "<p>消息标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message.content",
            "description": "<p>消息内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "message.createDate",
            "description": "<p>消息时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/MessageApi.java",
    "groupTitle": "message",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/message/systemInfo"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/message/team",
    "title": "球队消息",
    "name": "message_team",
    "group": "message",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>消息列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>消息id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.type",
            "description": "<p>消息类型（7：被邀请加入球队消息，8：邀请加入球队，9：申请加入球队, 10：约战成功,11：约战失败,12：申请约战, 13：申请加入球队回复）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.content",
            "description": "<p>消息内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.createDate",
            "description": "<p>消息时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.toUserId",
            "description": "<p>好友id (邀请加入球队)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.nickname",
            "description": "<p>好友昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.userId",
            "description": "<p>好友id (被邀请加入球队、申请加入球队)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.teamAvater",
            "description": "<p>球队队徽</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/MessageApi.java",
    "groupTitle": "message",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/message/team"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/message/watching",
    "title": "约看消息",
    "name": "message_watching",
    "group": "message",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>约看列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>约看id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.type",
            "description": "<p>类型（0：现场看球，1：直播看球）</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.user",
            "description": "<p>好友</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.user.id",
            "description": "<p>好友id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.user.nickname",
            "description": "<p>好友昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.bigRace",
            "description": "<p>现场看球</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.bigRace.id",
            "description": "<p>现场看球id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.bigRace.stadium.name",
            "description": "<p>现场看球球场名字</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.bigRace.startDate",
            "description": "<p>现场看球开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.watchingRace",
            "description": "<p>直播看球</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.watchingRace.id",
            "description": "<p>直播看球id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.watchingRace.name",
            "description": "<p>直播看球球馆名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.watchingRace.avater",
            "description": "<p>直播看球球馆封面</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.watchingRace.startTime",
            "description": "<p>直播看球开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.createDate",
            "description": "<p>记录生成时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/MessageApi.java",
    "groupTitle": "message",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/message/watching"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderBall/list",
    "title": "约球列表",
    "name": "orderBall_list",
    "group": "orderBall",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "timelimit",
            "description": "<p>时间期限</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "type",
            "description": "<p>球赛类型 N人制 N代表数量</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "areaId",
            "description": "<p>区域ID</p>"
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "longitude",
            "description": "<p>经度  &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "latitude",
            "description": "<p>纬度  &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNum",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>约球列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.id",
            "description": "<p>约球id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.content",
            "description": "<p>约球内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.matchType",
            "description": "<p>赛制</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.joinCount",
            "description": "<p>已报人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.lackCount",
            "description": "<p>剩余人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.user",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.user.nickname",
            "description": "<p>创建人昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.user.avater",
            "description": "<p>创建人头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.stadium",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "list.stadium.distance",
            "description": "<p>距离</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadium.name",
            "description": "<p>球场名字</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.stadium.type",
            "description": "<p>球场类型 (0:私人球场 1:公共球场)</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "page",
            "description": "<p>翻页信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalNum",
            "description": "<p>总记录数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.currentPage",
            "description": "<p>当前页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/OrderBallApi.java",
    "groupTitle": "orderBall",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/orderBall/list"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderBall/order",
    "title": "约球邀请",
    "name": "orderBall_order",
    "group": "orderBall",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "reserveId",
            "description": "<p>约球id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "toUserId",
            "description": "<p>好友id &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/OrderBallApi.java",
    "groupTitle": "orderBall",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/orderBall/order"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderBall/orderInfo",
    "title": "约球的详情",
    "name": "orderBall_orderInfo",
    "group": "orderBall",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "reserveId",
            "description": "<p>约球ID &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "reserveInfo",
            "description": "<p>约球列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "reserveInfo.id",
            "description": "<p>约球id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserveInfo.type",
            "description": "<p>约球类型（0：散客，1：公共）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserveInfo.status",
            "description": "<p>状态（0:正在组队1:组队成功2:组队失败3:比赛结束）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserveInfo.payment",
            "description": "<p>付款方式 （0:AA 1:全额）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "reserveInfo.content",
            "description": "<p>约球内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserveInfo.matchType",
            "description": "<p>赛制</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserveInfo.joinCount",
            "description": "<p>已报人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserveInfo.lackCount",
            "description": "<p>剩余人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "reserveInfo.user",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "reserveInfo.user.nickname",
            "description": "<p>创建人昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "reserveInfo.user.avater",
            "description": "<p>创建人头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "reserveInfo.stadium",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "reserveInfo.stadium.id",
            "description": "<p>球场id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "reserveInfo.stadium.name",
            "description": "<p>球场名字</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "reserveInfo.insurance",
            "description": "<p>保险</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "reserveInfo.insurance.id",
            "description": "<p>保险id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "reserveInfo.insurance.name",
            "description": "<p>保险名字</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "reserveInfo.insurance.price",
            "description": "<p>保险金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "reserveInfo.avePrice",
            "description": "<p>支付金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "reserveInfo.sumPrice",
            "description": "<p>AA制总金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "reserveInfo.userReservelist",
            "description": "<p>已报名球友列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "reserveInfo.userReservelist.id",
            "description": "<p>报名球友id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "reserveInfo.userReservelist.nickname",
            "description": "<p>报名球友昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "reserveInfo.userReservelist.avater",
            "description": "<p>报名球友头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "reserveInfo.userReservelist.startTime",
            "description": "<p>开始时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/OrderBallApi.java",
    "groupTitle": "orderBall",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/orderBall/orderInfo"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderBall/orderList",
    "title": "球友的约球列表",
    "name": "orderBall_orderList",
    "group": "orderBall",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "playerId",
            "description": "<p>球友ID &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>约球列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>约球id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.type",
            "description": "<p>约球类型（0：散客，1：公共）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.content",
            "description": "<p>约球内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.matchType",
            "description": "<p>赛制</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.joinCount",
            "description": "<p>已报人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.lackCount",
            "description": "<p>剩余人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.user",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.user.nickname",
            "description": "<p>创建人昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.user.avater",
            "description": "<p>创建人头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.stadium",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadium.name",
            "description": "<p>球场名字</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.startTime",
            "description": "<p>开始时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/OrderBallApi.java",
    "groupTitle": "orderBall",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/orderBall/orderList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderBall/pay",
    "title": "球友的约球支付",
    "name": "orderBall_pay",
    "group": "orderBall",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "reserveId",
            "description": "<p>约球ID &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "messageId",
            "description": "<p>约球消息ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "payInfo",
            "description": "<p>订单</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "payInfo.userName",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "payInfo.stadiumName",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "payInfo.price",
            "description": "<p>订单金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "payInfo.sn",
            "description": "<p>订单号</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/OrderBallApi.java",
    "groupTitle": "orderBall",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/orderBall/pay"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderBall/raceList",
    "title": "球友的赛事列表",
    "name": "orderBall_raceList",
    "group": "orderBall",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "playerId",
            "description": "<p>球友ID &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "schedule.watchBallVos",
            "description": "<p>球员所在球队为主队赛事列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.homeTeamName",
            "description": "<p>主队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.homeTeamAvater",
            "description": "<p>主队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.vTeamName",
            "description": "<p>客队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.vTeamAvater",
            "description": "<p>客队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "schedule.watchBallVos.status",
            "description": "<p>赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.stadiumName",
            "description": "<p>地址</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "schedule.watchBallVos.startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "schedule.watchBallVos.createDate",
            "description": "<p>发起时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "schedule.watchBallVoList",
            "description": "<p>球员所在球队为客队赛事列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.homeTeamName",
            "description": "<p>主队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.homeTeamAvater",
            "description": "<p>主队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.vTeamName",
            "description": "<p>客队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.vTeamAvater",
            "description": "<p>客队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "schedule.watchBallVoList.status",
            "description": "<p>赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.stadiumName",
            "description": "<p>地址</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "schedule.watchBallVoList.startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "schedule.watchBallVoList.createDate",
            "description": "<p>发起时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.mobile",
            "description": "<p>手机号</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/OrderBallApi.java",
    "groupTitle": "orderBall",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/orderBall/raceList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/pay/getPayInfo",
    "title": "拼接微信支付参数",
    "name": "pay_getPayInfo",
    "group": "pay",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "orderNum",
            "description": "<p>订单流水号       &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "payInfo",
            "description": "<p>微信支付信息</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "payInfo.appid",
            "description": "<p>应用APPID</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "payInfo.packages",
            "description": "<p>商户包信息</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "payInfo.prepayid",
            "description": "<p>预支付交易会话标识</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "payInfo.noncestr",
            "description": "<p>随机字符串</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "payInfo.partnerid",
            "description": "<p>商户号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "payInfo.timestamp",
            "description": "<p>时间字符串</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "payInfo.sign",
            "description": "<p>签名</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/PayCallBackApi.java",
    "groupTitle": "pay",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/pay/getPayInfo"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/playIndex/banner",
    "title": "系统banner图列表",
    "name": "playIndex_banner",
    "group": "playIndex",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>系统banner图列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>banner图id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.avater",
            "description": "<p>banner图</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.type",
            "description": "<p>banner图类型</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.toId",
            "description": "<p>banner图跳转目的Id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/PlayIndexApi.java",
    "groupTitle": "playIndex",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/playIndex/banner"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/playIndex/jump",
    "title": "系统banner图跳转",
    "name": "playIndex_jump",
    "group": "playIndex",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "bannerId",
            "description": "<p>banner图id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "banner.activity",
            "description": "<p>活动</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "banner.activity.id",
            "description": "<p>活动id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.activity.title",
            "description": "<p>活动标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.activity.avater",
            "description": "<p>活动封面</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.activity.introduction",
            "description": "<p>活动简介</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.activity.description",
            "description": "<p>活动介绍</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "banner.activity.createDate",
            "description": "<p>活动创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "banner.hostRace",
            "description": "<p>草根杯</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "banner.hostRace.id",
            "description": "<p>草根杯id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "banner.hostRace.type",
            "description": "<p>草根杯赛制</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.hostRace.name",
            "description": "<p>草根杯名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.hostRace.avater",
            "description": "<p>草根杯封面</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.hostRace.description",
            "description": "<p>草根杯介绍</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "banner.hostRace.stadium",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.hostRace.stadium.name",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "banner.information",
            "description": "<p>资讯列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "banner.information.id",
            "description": "<p>资讯id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.information.title",
            "description": "<p>资讯标题</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.information.avater",
            "description": "<p>资讯封面</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.information.introduction",
            "description": "<p>资讯简介</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "banner.information.description",
            "description": "<p>资讯介绍</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "banner.information.createDate",
            "description": "<p>资讯创建时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/PlayIndexApi.java",
    "groupTitle": "playIndex",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/playIndex/jump"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/playIndex/notice",
    "title": "通知",
    "name": "playIndex_notice",
    "group": "playIndex",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "reserve",
            "description": "<p>约球列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "reserve.id",
            "description": "<p>约球id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "reserve.content",
            "description": "<p>约球内容</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/PlayIndexApi.java",
    "groupTitle": "playIndex",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/playIndex/notice"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/playIndex/orderballList",
    "title": "我的赛事列表",
    "name": "playIndex_orderballList",
    "group": "playIndex",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>约球列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>约球id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.content",
            "description": "<p>约球内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.matchType",
            "description": "<p>赛制</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.joinCount",
            "description": "<p>已报人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.lackCount",
            "description": "<p>剩余人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.user",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.user.nickname",
            "description": "<p>创建人昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.user.avater",
            "description": "<p>创建人头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.stadium",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadium.name",
            "description": "<p>球场名字</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.createDate",
            "description": "<p>创建时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/PlayIndexApi.java",
    "groupTitle": "playIndex",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/playIndex/orderballList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/playIndex/teamRace",
    "title": "球队约战",
    "name": "playIndex_teamRace",
    "group": "playIndex",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>球队约战列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>球队id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.name",
            "description": "<p>球队名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.avater",
            "description": "<p>球队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.declareNum",
            "description": "<p>球队宣战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.battleNum",
            "description": "<p>球队应战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.list",
            "description": "<p>球队球员列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.list.id",
            "description": "<p>球队球员id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.list.avater",
            "description": "<p>球队球员头像</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/PlayIndexApi.java",
    "groupTitle": "playIndex",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/playIndex/teamRace"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/info",
    "title": "球场详情",
    "name": "stadium_info",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "stadiumId",
            "description": "<p>球场ID  &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "stadiumInfo",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "stadiumInfo.stadium",
            "description": "<p>球场详情</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "stadiumInfo.stadium.id",
            "description": "<p>球场id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumInfo.stadium.name",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumInfo.stadium.mobile",
            "description": "<p>球场联系方式</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumInfo.stadium.areaName",
            "description": "<p>球场地区名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumInfo.stadium.address",
            "description": "<p>球场地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumInfo.stadium.avater",
            "description": "<p>球场封面</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "stadiumInfo.stadium.siteType",
            "description": "<p>场地类型 (0:室内 1:室外)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumInfo.stadium.sodType",
            "description": "<p>草皮类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumInfo.stadium.light",
            "description": "<p>灯光</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "stadiumInfo.stadium.park",
            "description": "<p>停车场（ 0:无 1:免费 2:收费）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumInfo.stadium.giving",
            "description": "<p>赠送</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumInfo.stadium.description",
            "description": "<p>球场简介</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "stadiumInfo.time",
            "description": "<p>时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumInfo.time.week",
            "description": "<p>星期</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumInfo.time.date",
            "description": "<p>日期</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "stadiumInfo.time.time",
            "description": "<p>具体时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/stadium/info"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/insurance",
    "title": "保险",
    "name": "stadium_insurance",
    "group": "stadium",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>保险</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>预定id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.name",
            "description": "<p>保险名字</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "list.price",
            "description": "<p>保险价格</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/stadium/insurance"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/list",
    "title": "球场列表",
    "name": "stadium_list",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "type",
            "description": "<p>球赛类型 N人制 N代表数量</p>"
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "longitude",
            "description": "<p>经度  &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "latitude",
            "description": "<p>纬度  &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "cityId",
            "description": "<p>城市ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>区域列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.id",
            "description": "<p>区域id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.areaId",
            "description": "<p>区域名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadiumList",
            "description": "<p>区域球场</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadiumList.id",
            "description": "<p>球场id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadiumList.name",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "list.stadiumList.distance",
            "description": "<p>球场距离</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.stadiumList.type",
            "description": "<p>球场类型 （0:私人球场 1:公共球场）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.stadiumList.park",
            "description": "<p>是否有停车场 (0:无 1:免费 2:收费)</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadiumList.light",
            "description": "<p>灯光类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadiumList.giving",
            "description": "<p>赠送</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadiumList.areaName",
            "description": "<p>球场地区名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadiumList.address",
            "description": "<p>球场地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadiumList.avater",
            "description": "<p>球场封面</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/stadium/list"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/order",
    "title": "公共球场约散客",
    "name": "stadium_order",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "stadiumId",
            "description": "<p>球场ID &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "stadium",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "stadium.id",
            "description": "<p>球场id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadium.name",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadium.areaName",
            "description": "<p>球场地区名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadium.avater",
            "description": "<p>球场封面</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/stadium/order"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/pay",
    "title": "支付订单",
    "name": "stadium_pay",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "siteId",
            "description": "<p>预定场地ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "insuranceId",
            "description": "<p>保险ID</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "num",
            "description": "<p>购买保险数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "pay.sysInsurance",
            "description": "<p>保险</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "pay.sysInsurance.id",
            "description": "<p>保险id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "pay.sysInsurance.name",
            "description": "<p>保险名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "pay.sysInsurance.price",
            "description": "<p>保险金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "pay.siteMoney",
            "description": "<p>场地费</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "pay.vipNum",
            "description": "<p>会员等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "pay.preferente",
            "description": "<p>会员折扣</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "pay.money",
            "description": "<p>总金额</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/stadium/pay"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/payConfirm",
    "title": "确认支付订单",
    "name": "stadium_payConfirm",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "siteId",
            "description": "<p>预定场地ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "time",
            "description": "<p>时间戳  &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "start",
            "description": "<p>开始时间点  &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "end",
            "description": "<p>结束时间点  &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "type",
            "description": "<p>支付类型（0：全额支付  1：AA支付） &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "price",
            "description": "<p>金额 &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "insuranceId",
            "description": "<p>保险ID</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "num",
            "description": "<p>保险数 默认为0&lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "status",
            "description": "<p>状态（0：散客  1：球队） &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "payInfo",
            "description": "<p>订单</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "payInfo.price",
            "description": "<p>订单金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "payInfo.sn",
            "description": "<p>订单号</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/stadium/payConfirm"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/publish",
    "title": "公共球场发布",
    "name": "stadium_publish",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "stadiumId",
            "description": "<p>球场ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>标题  &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "time",
            "description": "<p>约球时间 &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "stadium",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "stadium.id",
            "description": "<p>球场id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadium.name",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "stadium.cityId",
            "description": "<p>球场地区</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadium.avater",
            "description": "<p>球场封面</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/stadium/publish"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/siteSelect",
    "title": "场次选择",
    "name": "stadium_siteSelect",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "stadiumId",
            "description": "<p>球场ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "time",
            "description": "<p>当天时间戳 &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>场地</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>场地id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.code",
            "description": "<p>场地编号</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.type",
            "description": "<p>场地类型  N人制</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.numList",
            "description": "<p>预定字符串</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.numList.status",
            "description": "<p>状态 (0：不可预定 1：可预订)</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/stadium/siteSelect"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/team/add",
    "title": "创建球队",
    "name": "team_add",
    "group": "team",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>球队名称 &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "slogan",
            "description": "<p>球队口号 &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Stream",
            "optional": false,
            "field": "avater",
            "description": "<p>队徽</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>球队地址</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "teamId",
            "description": "<p>球队id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/team/add"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/team/addFriend",
    "title": "邀请朋友加入球队",
    "name": "team_addFriend",
    "group": "team",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "toUserId",
            "description": "<p>好友id &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/team/addFriend"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/team/apply",
    "title": "申请加入球队",
    "name": "team_apply",
    "group": "team",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "teamId",
            "description": "<p>球队id &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/team/apply"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/team/info",
    "title": "球队详情",
    "name": "team_info",
    "group": "team",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "teamId",
            "description": "<p>球队Id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "teamInfo.team",
            "description": "<p>球队</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "teamInfo.team.id",
            "description": "<p>球队id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "teamInfo.team.name",
            "description": "<p>球队名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "teamInfo.team.avater",
            "description": "<p>球队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "teamInfo.team.slogan",
            "description": "<p>球队口号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "teamInfo.team.address",
            "description": "<p>球队地址</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "teamInfo.team.aveAge",
            "description": "<p>球队平均年龄</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "teamInfo.team.aveHeight",
            "description": "<p>球队平均身高</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "teamInfo.team.aveWeight",
            "description": "<p>球队平均体重</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "teamInfo.team.leaderUser",
            "description": "<p>队长</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "teamInfo.team.leaderUser.id",
            "description": "<p>队长id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "teamInfo.team.leaderUser.avater",
            "description": "<p>队长头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "teamInfo.userList",
            "description": "<p>队员</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "teamInfo.userList.id",
            "description": "<p>队员id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "teamInfo.userList.avater",
            "description": "<p>队员头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "teamInfo.userList.nickname",
            "description": "<p>队员昵称</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/team/info"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/team/list",
    "title": "球队列表",
    "name": "team_list",
    "group": "team",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>球队名字</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "cityId",
            "description": "<p>城市id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNum",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>球队列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>球队id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.name",
            "description": "<p>球队名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.avater",
            "description": "<p>球队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.count",
            "description": "<p>球队人数</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.address",
            "description": "<p>球队地址</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.num",
            "description": "<p>球队场次</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "page",
            "description": "<p>翻页信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalNum",
            "description": "<p>总记录数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.currentPage",
            "description": "<p>当前页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/team/list"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/team/order",
    "title": "约球队",
    "name": "team_order",
    "group": "team",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "team",
            "description": "<p>球队</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "team.id",
            "description": "<p>球队id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "team.name",
            "description": "<p>球队名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "team.avater",
            "description": "<p>球队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "team.address",
            "description": "<p>球队地址</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/team/order"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/team/orderInfo",
    "title": "约球队详情",
    "name": "team_orderInfo",
    "group": "team",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "team1Id",
            "description": "<p>主队Id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "team2Id",
            "description": "<p>客队Id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "time",
            "description": "<p>约球时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/team/orderInfo"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/team/raceAddress",
    "title": "球赛地址",
    "name": "team_raceAddress",
    "group": "team",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "teamRaceId",
            "description": "<p>球赛Id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>地址 &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/team/raceAddress"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/team/schedule",
    "title": "日程",
    "name": "team_schedule",
    "group": "team",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "teamId",
            "description": "<p>球队Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "schedule",
            "description": "<p>赛事列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "schedule.watchBallVos",
            "description": "<p>球员所在球队为主队赛事列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "schedule.watchBallVos.id",
            "description": "<p>赛事id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.homeTeamName",
            "description": "<p>主队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.homeTeamAvater",
            "description": "<p>主队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.vTeamName",
            "description": "<p>客队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.vTeamAvater",
            "description": "<p>客队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "schedule.watchBallVos.status",
            "description": "<p>赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.stadiumName",
            "description": "<p>地址</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "schedule.watchBallVos.startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "schedule.watchBallVos.createDate",
            "description": "<p>发起时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVos.mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "schedule.watchBallVoList",
            "description": "<p>球员所在球队为客队赛事列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "schedule.watchBallVoList.id",
            "description": "<p>赛事id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.homeTeamName",
            "description": "<p>主队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.homeTeamAvater",
            "description": "<p>主队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.vTeamName",
            "description": "<p>客队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.vTeamAvater",
            "description": "<p>客队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "schedule.watchBallVoList.status",
            "description": "<p>赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.stadiumName",
            "description": "<p>地址</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "schedule.watchBallVoList.startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "schedule.watchBallVoList.createDate",
            "description": "<p>发起时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "schedule.watchBallVoList.mobile",
            "description": "<p>手机号</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/team/schedule"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/test/test",
    "title": "场次选择",
    "name": "test_test",
    "group": "test",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>场地</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>场地id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.code",
            "description": "<p>场地编号</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.type",
            "description": "<p>场地类型  N人制</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.numList",
            "description": "<p>预定字符串</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.numList.status",
            "description": "<p>状态 (0：不可预定 1：可预订)</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/Test.java",
    "groupTitle": "test",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/test/test"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/comment",
    "title": "我的看球评价",
    "name": "user_comment",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "watchingId",
            "description": "<p>约看id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "star",
            "description": "<p>服务打分 &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>评论内容 &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/comment"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/commentList",
    "title": "我的评论",
    "name": "user_commentList",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNum",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>评论</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.tUser",
            "description": "<p>被评论用户</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.tUser.id",
            "description": "<p>被评论用户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.tUser.nickname",
            "description": "<p>被评论用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.post",
            "description": "<p>被评论帖子</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.post.content",
            "description": "<p>帖子内容</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.createDate",
            "description": "<p>评论时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "page",
            "description": "<p>翻页信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalNum",
            "description": "<p>总记录数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.currentPage",
            "description": "<p>当前页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/commentList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/confirm",
    "title": "我的看球确认",
    "name": "user_confirm",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "watchingId",
            "description": "<p>约看id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/confirm"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/edit",
    "title": "编辑个人资料",
    "name": "user_edit",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "gender",
            "description": "<p>用户性别（0：男 1：女）</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "birthday",
            "description": "<p>用户出生日期</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "provinceId",
            "description": "<p>用户省份</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "cityId",
            "description": "<p>用户城市</p>"
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "height",
            "description": "<p>身高</p>"
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "weight",
            "description": "<p>体重</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "position",
            "description": "<p>位置（0：前 1：中 2：后 3：守）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/edit"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/homePage",
    "title": "用户个人首页",
    "name": "user_homePage",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "userInfo",
            "description": "<p>用户</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "userInfo.user",
            "description": "<p>用户</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.user.nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.user.avater",
            "description": "<p>用户头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.user.vipNum",
            "description": "<p>用户会员等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.user.endDays",
            "description": "<p>用户会员天数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.user.credibility",
            "description": "<p>用户信誉评分</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.user.age",
            "description": "<p>用户年龄</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.user.gender",
            "description": "<p>用户性别 0:男 1：女</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "userInfo.user.height",
            "description": "<p>用户身高</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "userInfo.user.weight",
            "description": "<p>用户体重</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.user.position",
            "description": "<p>用户位置 0：前 1：中 2：后 3：守</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "userInfo.user.birthday",
            "description": "<p>用户出生日期</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "userInfo.user.cityId",
            "description": "<p>用户城市</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "userInfo.teamList",
            "description": "<p>加入的球队列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "userInfo.teamList.id",
            "description": "<p>球队id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.teamList.name",
            "description": "<p>球队名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.teamList.avater",
            "description": "<p>球队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.teamList.count",
            "description": "<p>球员总数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.teamList.battleNum",
            "description": "<p>球队应战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.teamList.declareNum",
            "description": "<p>球队宣战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "userInfo.teamList.list",
            "description": "<p>我加入的球队的球员列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "userInfo.teamList.list.id",
            "description": "<p>球员id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.teamList.list.avater",
            "description": "<p>球员头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "userInfo.myTeam",
            "description": "<p>自己创建的球队</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "userInfo.myTeam.id",
            "description": "<p>球队id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.myTeam.name",
            "description": "<p>球队名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.myTeam.avater",
            "description": "<p>球队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.myTeam.count",
            "description": "<p>球员总数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.myTeam.battleNum",
            "description": "<p>球队应战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userInfo.myTeam.declareNum",
            "description": "<p>球队宣战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "userInfo.myTeam.list",
            "description": "<p>我的球队的球员列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "userInfo.myTeam.list.id",
            "description": "<p>球员id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userInfo.myTeam.list.avater",
            "description": "<p>球员头像</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/homePage"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/message",
    "title": "说明",
    "name": "user_message",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "type",
            "description": "<p>类型 &lt;必传 /&gt; (1:信誉评分说明，2：足球宝贝服务说明，3：保险说明，4：约球须知，5：服务条款说明，6：会员等级说明，7：会员优惠说明，8：看球说明)</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>内容</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/message"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/operation",
    "title": "会员操作",
    "name": "user_operation",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "num",
            "description": "<p>会员时长（1：一年 2：两年 3：三年 默认为1）&lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "vip.status",
            "description": "<p>会员状态</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "vip.level",
            "description": "<p>会员等级</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "vip.endDate",
            "description": "<p>会员时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "vip.price",
            "description": "<p>价格</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "vip.experience",
            "description": "<p>当前经验值</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "vip.leftExperience",
            "description": "<p>到下一级经验值</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/operation"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/pay",
    "title": "会员付款",
    "name": "user_pay",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "num",
            "description": "<p>会员时长（1：一年 2：两年 3：三年 默认为1）&lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "price",
            "description": "<p>价格 &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "payInfo",
            "description": "<p>订单</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "payInfo.userName",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "payInfo.price",
            "description": "<p>订单金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "payInfo.sn",
            "description": "<p>订单号</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/pay"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/postInfo",
    "title": "我的帖子详情",
    "name": "user_postInfo",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "postId",
            "description": "<p>帖子id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "postInfo",
            "description": "<p>帖子</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postInfo.content",
            "description": "<p>帖子内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "postInfo.createDate",
            "description": "<p>帖子创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "postInfo.user",
            "description": "<p>用户列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "postInfo.user.id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postInfo.user.nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "postInfo.postImages",
            "description": "<p>帖子图片列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postInfo.postImages.avater",
            "description": "<p>帖子图片</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "postInfo.postComments",
            "description": "<p>帖子评论列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postInfo.postComments.content",
            "description": "<p>帖子内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "postInfo.postComments.createDate",
            "description": "<p>帖子创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "postInfo.postComments.fUser",
            "description": "<p>帖子评论人</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "postInfo.postComments.fUser.id",
            "description": "<p>帖子评论人id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postInfo.postComments.fUser.avater",
            "description": "<p>帖子评论人头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postInfo.postComments.fUser.nickname",
            "description": "<p>帖子评论人昵称</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/postInfo"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/postList",
    "title": "我的帖子",
    "name": "user_postList",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNum",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>帖子</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.user",
            "description": "<p>用户</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.user.avater",
            "description": "<p>用户头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.user.nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.postImage",
            "description": "<p>帖子图片列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.postImage.avater",
            "description": "<p>帖子图片</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.content",
            "description": "<p>帖子内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.commentNum",
            "description": "<p>帖子评论数</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.createDate",
            "description": "<p>创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "page",
            "description": "<p>翻页信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalNum",
            "description": "<p>总记录数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.currentPage",
            "description": "<p>当前页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/postList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/provinceList",
    "title": "查询省份列表",
    "name": "user_provinceList",
    "group": "user",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>省份列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.province",
            "description": "<p>省份</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.province.provinceId",
            "description": "<p>省份id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.province.province",
            "description": "<p>省份名字</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.province.cityList",
            "description": "<p>城市列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.province.cityList.city",
            "description": "<p>城市</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.province.cityList.city.id",
            "description": "<p>城市id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.province.cityList.city.city",
            "description": "<p>城市名字</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/provinceList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/report",
    "title": "问题反馈",
    "name": "user_report",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>联系方式</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>内容</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/report"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/siteList",
    "title": "我的场地",
    "name": "user_siteList",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "site",
            "description": "<p>用户场地列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "site.reserveList",
            "description": "<p>场地列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "site.reserveList.startTime",
            "description": "<p>预约时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "site.reserveList.stadium",
            "description": "<p>用户散客球场</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "site.reserveList.stadium.id",
            "description": "<p>球场id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "site.reserveList.stadium.name",
            "description": "<p>球场名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "site.reserveList.stadium.address",
            "description": "<p>球场地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "site.reserveList.stadium.areaName",
            "description": "<p>球场区域</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "site.reserveList.stadium.avater",
            "description": "<p>球场封面</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "site.reserveList.stadium.price",
            "description": "<p>场地价格</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/siteList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/watchingList",
    "title": "我的看球列表",
    "name": "user_watchingList",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传 /&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>用户约看列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>约看id</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "list.tip",
            "description": "<p>红包（小费）</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.startDate",
            "description": "<p>预约时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.status",
            "description": "<p>状态（0 ：未确认 1：已确认 2；已评价 ）</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.girl",
            "description": "<p>宝贝</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.girl.id",
            "description": "<p>宝贝id</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "list.girl.price",
            "description": "<p>宝贝价格</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.girl.age",
            "description": "<p>宝贝年龄</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "list.girl.height",
            "description": "<p>宝贝身高</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "list.girl.weight",
            "description": "<p>宝贝体重</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.girl.girlImageList",
            "description": "<p>宝贝封面列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.girl.girlImageList.id",
            "description": "<p>宝贝封面id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.girl.girlImageList.url",
            "description": "<p>宝贝封面路径</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.bigRace",
            "description": "<p>赛事</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.bigRace.id",
            "description": "<p>赛事id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.bigRace.team1name",
            "description": "<p>球队1的名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.bigRace.team2name",
            "description": "<p>球队2的名字</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.stadium",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.stadium.name",
            "description": "<p>球场名字</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/user/watchingList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/watching/girlComment",
    "title": "宝贝陪看评价",
    "name": "watching_girlComment",
    "group": "watching",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "girlId",
            "description": "<p>宝贝id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNum",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>宝贝陪看评价列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list.GirlComment",
            "description": "<p>宝贝陪看评价列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.GirlComment.id",
            "description": "<p>评价id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.GirlComment.star",
            "description": "<p>服务打分</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.GirlComment.content",
            "description": "<p>评论内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.GirlComment.createDate",
            "description": "<p>评论时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "page",
            "description": "<p>翻页信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalNum",
            "description": "<p>总记录数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.currentPage",
            "description": "<p>当前页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/WatchingApi.java",
    "groupTitle": "watching",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/watching/girlComment"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/watching/girlInfo",
    "title": "现场看球宝贝详情",
    "name": "watching_girlInfo",
    "group": "watching",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "girlId",
            "description": "<p>宝贝id &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "girlInfo.girl",
            "description": "<p>看球宝贝列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "girlInfo.girl.id",
            "description": "<p>宝贝id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "girlInfo.girl.name",
            "description": "<p>宝贝昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "girlInfo.girl.age",
            "description": "<p>宝贝年龄</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "girlInfo.girl.height",
            "description": "<p>宝贝身高</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "girlInfo.girl.weight",
            "description": "<p>宝贝体重</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "girlInfo.girl.price",
            "description": "<p>宝贝价格</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "girlInfo.girl.aveStar",
            "description": "<p>宝贝陪看评价</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "girlInfo.girl.orderNum",
            "description": "<p>宝贝预约次数</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "girlInfo.girl.interest",
            "description": "<p>宝贝兴趣</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "girlInfo.girl.profession",
            "description": "<p>宝贝职业</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "girlInfo.girl.favoriteTeam",
            "description": "<p>宝贝喜欢球队</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "girlInfo.girl.label",
            "description": "<p>宝贝签名</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "girlInfo.girl.cityId",
            "description": "<p>宝贝陪看区域</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "girlInfo.girlImages1",
            "description": "<p>宝贝相册列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "girlInfo.girlImages1.id",
            "description": "<p>宝贝相册id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "girlInfo.girlImages1.avater",
            "description": "<p>宝贝相册</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "girlInfo.girlImages",
            "description": "<p>宝贝封面列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "girlInfo.girlImages.id",
            "description": "<p>宝贝封面id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "girlInfo.girlImages.avater",
            "description": "<p>宝贝封面</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/WatchingApi.java",
    "groupTitle": "watching",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/watching/girlInfo"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/watching/inviteBall",
    "title": "看球邀请",
    "name": "watching_inviteBall",
    "group": "watching",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "type",
            "description": "<p>类型（1：直播看球，0：现场看球） &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "id",
            "description": "<p>看球id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "toUserId",
            "description": "<p>好友id &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/WatchingApi.java",
    "groupTitle": "watching",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/watching/inviteBall"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/watching/orderGirl",
    "title": "约宝贝看球确认",
    "name": "watching_orderGirl",
    "group": "watching",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "girlId",
            "description": "<p>宝贝id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "sceneId",
            "description": "<p>比赛id &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "tip",
            "description": "<p>红包费</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "order",
            "description": "<p>订单</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "order.price",
            "description": "<p>订单金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "order.sn",
            "description": "<p>订单号</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/WatchingApi.java",
    "groupTitle": "watching",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/watching/orderGirl"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/watching/sceneList",
    "title": "现场看球列表",
    "name": "watching_sceneList",
    "group": "watching",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "cityId",
            "description": "<p>城市id &lt;必传/&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "info.bigRace",
            "description": "<p>现场看球列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "info.bigRace.id",
            "description": "<p>看球id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "info.bigRace.team1name",
            "description": "<p>球队1名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "info.bigRace.avater1",
            "description": "<p>球队1队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "info.bigRace.team2name",
            "description": "<p>球队2名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "info.bigRace.avater2",
            "description": "<p>球队2队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "info.bigRace.startDate",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "info.bigRace.stadium",
            "description": "<p>现场看球球场</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "info.bigRace.stadium.name",
            "description": "<p>球场名字</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "info.bigRace.description",
            "description": "<p>看球描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "info.girlImageList",
            "description": "<p>宝贝</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "info.girlImageList.id",
            "description": "<p>图片id</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "info.girlImageList.girlId",
            "description": "<p>宝贝id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "info.girlImageList.url",
            "description": "<p>宝贝封面</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "info.girlImageList.name",
            "description": "<p>宝贝名字</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "info.girlImageList.age",
            "description": "<p>宝贝年龄</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "info.girlImageList.height",
            "description": "<p>宝贝身高</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "info.girlImageList.weight",
            "description": "<p>宝贝体重</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/WatchingApi.java",
    "groupTitle": "watching",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/watching/sceneList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/watching/telecastList",
    "title": "直播看球列表",
    "name": "watching_telecastList",
    "group": "watching",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageNum",
            "description": "<p>当前页</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "list",
            "description": "<p>直播看球列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "list.id",
            "description": "<p>看球id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.name",
            "description": "<p>看球名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.avater",
            "description": "<p>封面</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "page",
            "description": "<p>翻页信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalNum",
            "description": "<p>总记录数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.totalPage",
            "description": "<p>总页数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "page.currentPage",
            "description": "<p>当前页</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/WatchingApi.java",
    "groupTitle": "watching",
    "sampleRequest": [
      {
        "url": "http://4.16.1.158:8080/yqtq_app/api/watching/telecastList"
      }
    ]
  }
] });
