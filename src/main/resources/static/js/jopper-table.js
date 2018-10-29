Vue.component('jopper-table', {
    props: {
        resource: {type: String, default() {return ''}},
        columns: {type: Array, default() {return []}},
        operations: {type: Array, default() {return []}},
        rows: {type: Array, default() {return []}},
        queryForm: {type: Object, default() {return {}}},
        others: {type: Array, default() {return []}}
    },
    template: `<div class="jopper-table">
    <table>
        <thead>
        <tr>
            <td v-for="column in columns" :key="column.key">{{ column.name }}</td>
            <td v-show="hasOperations"></td>
        </tr>
        </thead>
        <tbody>
            <tr v-for="row in rows">
                <td v-for="cell in row">{{ cell }}</td>
                <td v-show="hasOperations">
                    <button v-for="operation in operations">{{ operation.name }}</button>
                </td>
            </tr>
        </tbody>
    </table>
</div>
`,
    updated() {
        console.log('vue instance updated: ', this.othersLength());
    },
    mounted() {
        console.log('vue instance mounted: ', this.othersLength());

        let vm = this;
        let metaUrl = 'resources/' + this.resource + "/meta";
        let queryUrl = 'resources/' + this.resource + "/query";
        axios.get(metaUrl)
            .then(function (response) {
                Jopper.setArray(vm, 'columns', response.data.columns);
                Jopper.setArray(vm, 'operations', response.data.operations);
            })
            .then(function() {
                axios.get(queryUrl)
                    .then(function(response) {
                        Jopper.setArray(vm, 'rows', response.data.list);
                    })
            });
    },
    methods: {
        hasOperations() {
            return this.operations && this.operations.length > 0;
        },
        othersLength() {
            return this.others === undefined? 'undefined': this.others.length;
        }
    }
});

window.onReady(function () {
    let elementName = 'jopper-table';
    document.querySelectorAll(elementName).forEach(function (elem) {
        window.vm = new Vue({
            el: elem, data: {
                columns: [{
                    name: 'AAA', key: 'aaa'
                }],
                operations: [],
                rows: [],
                queryForm: {},
                others: ['1', '2', '3'],
            }
        });
    });
});