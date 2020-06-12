import tableMixin from 'mixins/table/table';
import carSelectMixin from 'mixins/select_car';
import commonMixin from 'views/numerical_statement/js/common';
import * as API from 'api/numerical_statement/driver_attendance';

export default {
   name: 'ReportDriverAttendance',
   mixins: [tableMixin, carSelectMixin, commonMixin],
   data() {
      return {
         table_loading: false,
         download_loading: false,
         search_show: true,
         data: null,
         params: {
            page: 1,
            deptId: '',
            cardState: '',
            date: []
         }
      }
   },
   created() {
      const date = this.moment().format('YYYY-MM-DD');
      this.params.date = [`${date} 00:00:00`, `${date} 23:59:59`];
      this.init();
   },
   beforeDestroy() {
      this.departments = [];
      this.data = null;
   },
   methods: {
      init() {
         this.getDepartmentalList();
         this.getMenuFuncs();
         this.index(1);
      },

      /**
       * 列表
       * @param page
       */
      index(page) {
         $('#elTable .el-table__body-wrapper').scrollTop(0);
         if (page) {
            this.params.page = page;
         }
         let data = this.returnSearchData();
         this.table_loading = true;
         API.index(data).then(res => {
            this.table_loading = false;
            if (res.code === 10000) {
               this.data = {
                  total: res.total,
                  rows: res.rows
               };
            }
         }).catch(err => {
            this.table_loading = false;
         });
      },

      /**
       * 处理搜索数据
       */
      returnSearchData(isExport = false) {
         const params = this.params;
         let data = {};
         for (let k of Object.keys(params)) {
            if (isExport) {
               if (['page', 'rows'].includes(k)) {
                  continue;
               }
            }

            if (k == 'date') {
               if (params[k].length && params[k][0] && params[k][1]) {
                  data.startQueryTime = this.moment(params[k][0]).format('YYYY-MM-DD HH:mm:ss');
                  data.endQueryTime = this.moment(params[k][1]).format('YYYY-MM-DD HH:mm:ss');
               }
            } else if (params[k]) {
               data[k] = params[k];
            }
         }
         return data;
      },

      /**
       * 重置搜索
       */
      resetSearch() {
         for (let k of ['plateNo', 'simNo', 'cardState', 'vehicleId','deptId']) {
            this.params[k] = '';
         }
         const date = this.moment().format('YYYY-MM-DD');
         this.params.date = [`${date} 00:00:00`, `${date} 23:59:59`];
         this.index(1);
      },

      /**
       * 导出表格
       */
      exportTable() {
         this.download_loading = true;
         let data = this.returnSearchData(true);
         API.exportExcel(data).then(res => {
            this.download_loading = false;
            if (res && res.data) {
               this.exportExcels(res.data, `司机考勤管理-${this.moment().format('YYYY-MM-DD')}.xlsx`);
            }
         }).catch(err => {
            this.download_loading = false;
         });
      }
   }
}
