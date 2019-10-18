Vue.component('jopper-table', {
  props: {
    resource: {type: String, default: ''},
    columns: {
      type: Array, default() {
        return [{name: "表格加载中...", key: ''}]
      }
    },
    operations: {
      type: Array, default() {
        return []
      }
    },
    rows: {
      type: Array, default() {
        return []
      }
    },
    autoQuery: {
      type: Object, default() {
        return {value: false}
      }
    }
  },
  template: `<div class="jopper-table">
    <table class="table">
        <thead>
        <tr>
            <th v-for="column in columns" :key="column.key">{{ column.name }}</th>
            <th v-show="hasOperations()"></th>
        </tr>
        </thead>
        <tbody>
            <tr v-for="row in rows">
                <td v-for="column in columns" :key="column.key">{{ row[column.key] }}</td>
                <td v-show="hasOperations">
                    <button class="btn btn-primary btn-sm mr-1"
                    @click="doOperation(operation, row)" 
                    v-for="operation in operations">
                    <i v-if="operation.icon" :class="operation.icon"></i>
                    {{ operation.icon? '': operation.name }}
                    </button>
                </td>
            </tr>
        </tbody>
    </table>
</div>
`,
  mounted() {
    let vm = this;
    let metaUrl = 'resources/' + this.resource + "/meta.json";
    let queryUrl = 'resources/' + this.resource + "/query";
    axios.get(metaUrl)
    .then(function (response) {
      Jopper.setArray(vm, 'columns', response.data.columns);
      Jopper.setArray(vm, 'operations', response.data.operations);
      vm.autoQuery.value = response.data.autoQuery;
    })
    .then(function () {
      vm.loadData();
    });
  },
  methods: {
    loadData() {
      let vm = this;
      if (!vm.autoQuery.value) {
        return;
      }
      let queryUrl = 'resources/' + this.resource + "/query";
      axios.get(queryUrl).then(function (response) {
        Jopper.setArray(vm, 'rows', response.data.list);
      })
    },
    hasOperations() {
      return this.operations && this.operations.length > 0;
    },
    doOperation(operation, row) {
      var f = function () {
        eval(operation.action);
      };
      f.apply(row);
    }
  }
});

window.onReady(function () {
  let elementName = 'jopper-table';
  document.querySelectorAll(elementName).forEach(function (elem) {
    window.vm = new Vue({el: elem});
  });
});