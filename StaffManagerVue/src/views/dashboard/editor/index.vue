<template>
  <div class="dashboard-editor-container">
    <div>
      <div class="info-container">
        <span class="display_name">欢迎您，{{ name }}</span>
      </div>
    </div>
    <div>
      你的权限:
      <span v-for="item in roles" :key="item" class="pan-info-roles">{{
        item
      }}</span>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import PanThumb from "@/components/PanThumb";
import GithubCorner from "@/components/GithubCorner";

export default {
  name: "DashboardEditor",
  components: { PanThumb, GithubCorner },
  data() {
    return {
      emptyGif:
        "https://wpimg.wallstcn.com/0e03b7da-db9e-4819-ba10-9016ddfdaed3",
      value: new Date(),
    };
  },
  computed: {
    ...mapGetters(["name", "avatar", "roles"]),
  },
};
</script>

<style lang="scss" scoped>
.el-calendar-table .el-calendar-day {
  height: 30px;
}
.emptyGif {
  display: block;
  width: 45%;
  margin: 0 auto;
}

.dashboard-editor-container {
  background-color: #e3e3e3;
  padding: 30px 0 30px 30px;
  .pan-info-roles {
    font-size: 22px;
    font-weight: 700;
    color: #333;
    display: block;
  }
  .info-container {
    position: relative;
    margin-left: 190px;
    .display_name {
      font-size: 32px;
      line-height: 32px;
      color: #212121;
      position: absolute;
      top: 20px;
    }
  }
}
</style>
