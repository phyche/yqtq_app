define({ "api": [
  {
    "type": "post",
    "url": "/api/orderball/info",
    "title": "球友个人资料",
    "name": "orderball_info",
    "group": "orderball",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
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
            "field": "user",
            "description": "<p>球友列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "user.avater",
            "description": "<p>球友头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "user.nickname",
            "description": "<p>球友昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.vipNum",
            "description": "<p>球友会员等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.credibility",
            "description": "<p>球友信誉评分</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.age",
            "description": "<p>球友年龄</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.gender",
            "description": "<p>球友性别 0:男 1：女</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "user.height",
            "description": "<p>球友身高</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "user.weight",
            "description": "<p>球友体重</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.position",
            "description": "<p>球友位置 0：前 1：中 2：后 3：守</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "team",
            "description": "<p>球友的球队</p>"
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
            "field": "team.name",
            "description": "<p>球队名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "team.declareNum",
            "description": "<p>球队宣战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "team.battleNum",
            "description": "<p>球队应战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "team.count",
            "description": "<p>球队总人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "team.list",
            "description": "<p>球队球员列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "team.list.avater",
            "description": "<p>球队球员头像</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/OrderBallApi.java",
    "groupTitle": "orderball",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/orderball/info"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderball/list",
    "title": "约球列表",
    "name": "orderball_list",
    "group": "orderball",
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
            "type": "Integer",
            "optional": false,
            "field": "areaId",
            "description": "<p>区域ID</p>"
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
            "type": "Integer",
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
    "groupTitle": "orderball",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/orderball/list"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderball/orderInfo",
    "title": "约球的详情",
    "name": "orderball_orderInfo",
    "group": "orderball",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
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
            "field": "reserve",
            "description": "<p>约球列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserve.id",
            "description": "<p>约球id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserve.content",
            "description": "<p>约球内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserve.matchType",
            "description": "<p>赛制</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserve.joinCount",
            "description": "<p>已报人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserve.lackCount",
            "description": "<p>剩余人数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "reserve.user",
            "description": "<p>创建人</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "reserve.user.nickname",
            "description": "<p>创建人昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "reserve.user.avater",
            "description": "<p>创建人头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "reserve.stadium",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserve.stadium.id",
            "description": "<p>球场id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "reserve.stadium.name",
            "description": "<p>球场名字</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "reserve.avePrice",
            "description": "<p>AA制金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "reserve.sumPrice",
            "description": "<p>支付总金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "userList",
            "description": "<p>已报名球友列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userList.id",
            "description": "<p>报名球友id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userList.nickname",
            "description": "<p>报名球友昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userList.avater",
            "description": "<p>报名球友头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "reserve.startTime",
            "description": "<p>开始时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/OrderBallApi.java",
    "groupTitle": "orderball",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/orderball/orderInfo"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderball/orderList",
    "title": "球友的约球列表",
    "name": "orderball_orderList",
    "group": "orderball",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
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
            "type": "Integer",
            "optional": false,
            "field": "list.id",
            "description": "<p>约球id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
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
    "groupTitle": "orderball",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/orderball/orderList"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderball/pay",
    "title": "球友的约球支付",
    "name": "orderball_pay",
    "group": "orderball",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "reserveId",
            "description": "<p>约球ID &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>赛事ID &lt;必传 /&gt;</p>"
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
            "type": "String",
            "optional": false,
            "field": "order.userName",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "order.stadiumName",
            "description": "<p>球场名称</p>"
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
    "filename": "src/main/java/com/sixmac/controller/api/OrderBallApi.java",
    "groupTitle": "orderball",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/orderball/pay"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderball/raceInfo",
    "title": "球友的赛事详情",
    "name": "orderball_raceInfo",
    "group": "orderball",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>球友ID &lt;必传 /&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "raceId",
            "description": "<p>赛事ID &lt;必传 /&gt;</p>"
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
            "field": "watchBallVo",
            "description": "<p>球员赛事</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVo.homeTeamName",
            "description": "<p>主队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVo.homeTeamAvater",
            "description": "<p>主队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVo.vTeamName",
            "description": "<p>客队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVo.vTeamAvater",
            "description": "<p>客队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "watchBallVo.status",
            "description": "<p>赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVo.stadiumName",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "watchBallVo.startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/OrderBallApi.java",
    "groupTitle": "orderball",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/orderball/raceInfo"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/orderball/raceList",
    "title": "球友的赛事列表",
    "name": "orderball_raceList",
    "group": "orderball",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
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
            "field": "watchBallVos",
            "description": "<p>球员所在球队为主队赛事列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVos.homeTeamName",
            "description": "<p>主队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVos.homeTeamAvater",
            "description": "<p>主队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVos.vTeamName",
            "description": "<p>客队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVos.vTeamAvater",
            "description": "<p>客队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "watchBallVos.status",
            "description": "<p>赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVos.stadiumName",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "watchBallVos.startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "watchBallVoList",
            "description": "<p>球员所在球队为客队赛事列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVoList.homeTeamName",
            "description": "<p>主队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVoList.homeTeamAvater",
            "description": "<p>主队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVoList.vTeamName",
            "description": "<p>客队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVoList.vTeamAvater",
            "description": "<p>客队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "watchBallVoList.status",
            "description": "<p>赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVoList.stadiumName",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "watchBallVoList.startTime",
            "description": "<p>开始时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/OrderBallApi.java",
    "groupTitle": "orderball",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/orderball/raceList"
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
            "type": "Integer",
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
            "field": "stadium",
            "description": "<p>球场</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
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
            "type": "Integer",
            "optional": false,
            "field": "stadium.cityId",
            "description": "<p>球场地区</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadium.address",
            "description": "<p>球场地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadium.avater",
            "description": "<p>球场封面</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadium.siteType",
            "description": "<p>场地类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadium.sodType",
            "description": "<p>草皮类型</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "stadium.light",
            "description": "<p>灯光(0：有灯光 1：没有灯光)</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "stadium.park",
            "description": "<p>停车场（0：有停车场 1；没有停车场）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadium.giving",
            "description": "<p>赠送</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadium.description",
            "description": "<p>球场简介</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "sites",
            "description": "<p>球场场地</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/stadium/info"
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
            "type": "Integer",
            "optional": false,
            "field": "areaId",
            "description": "<p>区域ID</p>"
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
            "description": "<p>球场列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.id",
            "description": "<p>球场id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.name",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.type",
            "description": "<p>球场类型 （0:私人球场 1:公共球场）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "list.cityId",
            "description": "<p>球场地区</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.address",
            "description": "<p>球场地址</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "list.avater",
            "description": "<p>球场封面</p>"
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
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/stadium/list"
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
            "type": "Integer",
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
            "type": "Integer",
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
            "type": "Integer",
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
        "url": "http://localhost:8080/yqtq_app/api/stadium/order"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/pay",
    "title": "散客支付订单",
    "name": "stadium_pay",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "siteId",
            "description": "<p>场地ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID（0：不买  1：买） &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
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
            "field": "site",
            "description": "<p>场地</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "sysInsurance",
            "description": "<p>保险</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "sysInsurance.id",
            "description": "<p>保险id</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "sysInsurance.name",
            "description": "<p>保险名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "sysInsurance.price",
            "description": "<p>保险金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "siteMoney",
            "description": "<p>场地费</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "vipNum",
            "description": "<p>会员等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "preferente",
            "description": "<p>会员折扣</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "money",
            "description": "<p>总金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "reserve",
            "description": "<p>散客预定（约球）</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserve.id",
            "description": "<p>预定id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/stadium/pay"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/payConfirm",
    "title": "散客确认支付订单",
    "name": "stadium_payConfirm",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "reserveId",
            "description": "<p>预定ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "type",
            "description": "<p>支付类型（0：全额支付  1：AA支付） &lt;必传/&gt;</p>"
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
            "type": "String",
            "optional": false,
            "field": "order.userName",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "order.stadiumName",
            "description": "<p>球场名称</p>"
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
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/stadium/payConfirm"
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
            "type": "Integer",
            "optional": false,
            "field": "stadiumId",
            "description": "<p>球场ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
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
            "type": "Integer",
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
            "type": "Integer",
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
        "url": "http://localhost:8080/yqtq_app/api/stadium/publish"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/siteOrder",
    "title": "场地预订",
    "name": "stadium_siteOrder",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "siteId",
            "description": "<p>场地ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "status",
            "description": "<p>运动保险状态（0：不买  1：买） &lt;必传/&gt;</p>"
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
            "description": "<p>场地</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "stadiumVo",
            "description": "<p>场地</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "stadiumVo.id",
            "description": "<p>球场id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "stadiumVo.name",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "stadiumVo.cityId",
            "description": "<p>球场地区</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/stadium/siteOrder"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/teamPay",
    "title": "球队支付订单",
    "name": "stadium_teamPay",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "siteId",
            "description": "<p>场地ID &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户ID（0：不买  1：买） &lt;必传/&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
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
            "field": "site",
            "description": "<p>场地</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "sysInsurance",
            "description": "<p>保险</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "sysInsurance.name",
            "description": "<p>保险名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "sysInsurance.price",
            "description": "<p>保险金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "siteMoney",
            "description": "<p>场地费</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "vipNum",
            "description": "<p>会员等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "preferente",
            "description": "<p>会员折扣</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "money",
            "description": "<p>总金额</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "reserveTeam",
            "description": "<p>球队预定</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "reserveTeam.id",
            "description": "<p>预定id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/stadium/teamPay"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/stadium/teamPayConfirm",
    "title": "球队确认支付订单",
    "name": "stadium_teamPayConfirm",
    "group": "stadium",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "reserveTeamId",
            "description": "<p>预定ID &lt;必传/&gt;</p>"
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
            "type": "String",
            "optional": false,
            "field": "order.userName",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "order.stadiumName",
            "description": "<p>球场名称</p>"
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
    "filename": "src/main/java/com/sixmac/controller/api/StadiumApi.java",
    "groupTitle": "stadium",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/stadium/teamPayConfirm"
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
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>球队名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "avater",
            "description": "<p>球队队徽</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "slogan",
            "description": "<p>球队口号</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "address",
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
        "url": "http://localhost:8080/yqtq_app/api/team/add"
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
            "type": "Integer",
            "optional": false,
            "field": "teamId",
            "description": "<p>球队Id</p>"
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
            "type": "Integer",
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
            "type": "Integer",
            "optional": false,
            "field": "team.slogan",
            "description": "<p>球队口号</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "team.address",
            "description": "<p>球队地址</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "team.aveAge",
            "description": "<p>球队平均年龄</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "team.aveHeight",
            "description": "<p>球队平均身高</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "team.aveWeight",
            "description": "<p>球队平均体重</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "team.leaderUser",
            "description": "<p>队长</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "team.leaderUser.id",
            "description": "<p>队长id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "team.leaderUser.avater",
            "description": "<p>队长头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "userList",
            "description": "<p>队员</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userList.id",
            "description": "<p>队员id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userList.avater",
            "description": "<p>队员头像</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/team/info"
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
            "type": "Integer",
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
            "type": "Integer",
            "optional": false,
            "field": "list.cityId",
            "description": "<p>球队地区</p>"
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
        "url": "http://localhost:8080/yqtq_app/api/team/list"
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
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "teamId",
            "description": "<p>球队Id</p>"
          },
          {
            "group": "Parameter",
            "type": "Long",
            "optional": false,
            "field": "time",
            "description": "<p>约球时间</p>"
          },
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "address",
            "description": "<p>约球地址</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/team/order"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/team/playerList",
    "title": "球员列表",
    "name": "team_playerList",
    "group": "team",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "teamId",
            "description": "<p>球队Id</p>"
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
            "field": "userList",
            "description": "<p>队员</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "userList.id",
            "description": "<p>队员id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userList.name",
            "description": "<p>队员昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userList.avater",
            "description": "<p>队员头像</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/team/playerList"
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
            "type": "Integer",
            "optional": false,
            "field": "teamId",
            "description": "<p>球队Id</p>"
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
            "field": "watchBallVos",
            "description": "<p>球员所在球队为主队赛事列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "watchBallVos.id",
            "description": "<p>赛事id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVos.homeTeamName",
            "description": "<p>主队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVos.homeTeamAvater",
            "description": "<p>主队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVos.vTeamName",
            "description": "<p>客队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVos.vTeamAvater",
            "description": "<p>客队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "watchBallVos.status",
            "description": "<p>赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVos.stadiumName",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "watchBallVos.startTime",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "watchBallVoList",
            "description": "<p>球员所在球队为客队赛事列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "watchBallVoList.id",
            "description": "<p>赛事id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVoList.homeTeamName",
            "description": "<p>主队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVoList.homeTeamAvater",
            "description": "<p>主队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVoList.vTeamName",
            "description": "<p>客队队名</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVoList.vTeamAvater",
            "description": "<p>客队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "watchBallVoList.status",
            "description": "<p>赛事状态 （0：等待同意，1：约赛成功，2：约赛失败）</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "watchBallVoList.stadiumName",
            "description": "<p>球场名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "watchBallVoList.startTime",
            "description": "<p>开始时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/TeamApi.java",
    "groupTitle": "team",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/team/schedule"
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
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
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
            "field": "tUser",
            "description": "<p>被评论用户</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "tUser.id",
            "description": "<p>被评论用户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "tUser.nickname",
            "description": "<p>被评论用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>帖子内容</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "createDate",
            "description": "<p>评论时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/user/commentList"
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
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "avater",
            "description": "<p>用户头像</p>"
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
            "type": "Integer",
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
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "teamList",
            "description": "<p>我加入的球队列表</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/user/edit"
      }
    ]
  },
  {
    "type": "post",
    "url": "/api/user/info",
    "title": "用户个人资料",
    "name": "user_info",
    "group": "user",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
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
            "description": "<p>用户</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "user.nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "user.avater",
            "description": "<p>用户头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.vipNum",
            "description": "<p>用户会员等级</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "user.credibility",
            "description": "<p>用户信誉评分</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "teamList",
            "description": "<p>我加入的球队列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "teamList.id",
            "description": "<p>球队id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "teamList.name",
            "description": "<p>球队名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "teamList.avater",
            "description": "<p>球队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "teamList.count",
            "description": "<p>球员总数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "teamList.battleNum",
            "description": "<p>球队应战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "teamList.declareNum",
            "description": "<p>球队宣战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "teamList.list",
            "description": "<p>我加入的球队的球员列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "teamList.list.id",
            "description": "<p>球员id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "teamList.list.avater",
            "description": "<p>球员头像</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "myTeam",
            "description": "<p>我的球队</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "myTeam.id",
            "description": "<p>球队id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "myTeam.name",
            "description": "<p>球队名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "myTeam.avater",
            "description": "<p>球队队徽</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "myTeam.count",
            "description": "<p>球员总数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "myTeam.battleNum",
            "description": "<p>球队应战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "myTeam.declareNum",
            "description": "<p>球队宣战数</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "myTeam.list",
            "description": "<p>我的球队的球员列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "myTeam.list.id",
            "description": "<p>球员id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "myTeam.list.avater",
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
        "url": "http://localhost:8080/yqtq_app/api/user/info"
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
            "type": "Integer",
            "optional": false,
            "field": "postId",
            "description": "<p>帖子id</p>"
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
            "field": "post",
            "description": "<p>帖子</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "post.content",
            "description": "<p>帖子内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "post.createDate",
            "description": "<p>帖子创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "post.user",
            "description": "<p>用户列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "post.user.id",
            "description": "<p>用户id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "post.user.nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "postImages",
            "description": "<p>帖子图片列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postImages.avater",
            "description": "<p>帖子图片</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "postComments",
            "description": "<p>帖子评论列表</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "postComments.content",
            "description": "<p>帖子内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "postComments.createDate",
            "description": "<p>帖子创建时间</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "postComments.fUser",
            "description": "<p>帖子评论人</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "postComments.fUser.id",
            "description": "<p>帖子评论人id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postComments.fUser.avater",
            "description": "<p>帖子评论人头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postComments.fUser.nickname",
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
        "url": "http://localhost:8080/yqtq_app/api/user/postInfo"
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
            "type": "Integer",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
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
            "field": "postList",
            "description": "<p>帖子</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "postList.user",
            "description": "<p>用户</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postList.user.avater",
            "description": "<p>用户头像</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postList.user.nickname",
            "description": "<p>用户昵称</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "postList.postImage",
            "description": "<p>帖子图片列表</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postList.postImage.avater",
            "description": "<p>帖子图片</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "postList.content",
            "description": "<p>帖子内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "postList.commentNum",
            "description": "<p>帖子评论数</p>"
          },
          {
            "group": "Success 200",
            "type": "Long",
            "optional": false,
            "field": "postList.createDate",
            "description": "<p>创建时间</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/sixmac/controller/api/UserApi.java",
    "groupTitle": "user",
    "sampleRequest": [
      {
        "url": "http://localhost:8080/yqtq_app/api/user/postList"
      }
    ]
  }
] });
