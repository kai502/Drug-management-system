<template>
  <Alert>
    新的通知<br/>
    <Time :time="time1" />
    <template #desc>
      S13全球总决赛决赛的比赛中，T1战队是轻松横扫WBG战队拿下冠军，WBG战队被观众寄予厚望的The Shy选手，在对阵Zeus的三局游戏中被彻底碾压。
    </template>
  </Alert>
  <Alert type="success">
    紧急通知<br/>
    <Time :time="time1" />
    <template #desc>
      S13全球总决赛决赛的比赛中，T1战队是轻松横扫WBG战队拿下冠军，WBG战队被观众寄予厚望的The Shy选手，在对阵Zeus的三局游戏中被彻底碾压。
    </template>
  </Alert>
  <Alert type="warning">
    警告通知<br/>
    <Time :time="time1" />
    <template #desc>
      S13全球总决赛决赛的比赛中，T1战队是轻松横扫WBG战队拿下冠军，WBG战队被观众寄予厚望的The Shy选手，在对阵Zeus的三局游戏中被彻底碾压。
    </template>
  </Alert>
  <Alert type="error">
    错误通知<br/>
    <Time :time="time1" />
    <template #desc>
      S13全球总决赛决赛的比赛中，T1战队是轻松横扫WBG战队拿下冠军，WBG战队被观众寄予厚望的The Shy选手，在对阵Zeus的三局游戏中被彻底碾压。
      <Icon type="help-circled" size="14"></Icon>
    </template>
  </Alert>
</template>
<script>
export default {
  data () {
    return {
      time1: (new Date()).getTime() - 60 * 3 * 1000,
      time2: (new Date()).getTime() - 86400 * 3 * 1000
    }
  }
}
</script>
