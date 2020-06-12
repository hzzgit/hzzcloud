<template>
   <div>
      <!--<button @click="$router.push('/test2')">test2</button>-->
      <div id="tableSearch">
         <ul class="g-header bd-b">
            <li class="f-l">
               <div class="g-header-tip"><b>{{(permissions_object && permissions_object[$route.query.mid])?permissions_object[$route.query.mid].title:''}}</b></div>
            </li>
            <li class="f-r">
               <div class="g-header-item" v-if="hasNodes('3')">
                  <Button type="primary" icon="md-download" :loading="download_loading" @click="exportTable">导出</Button>
               </div>
               <div class="g-header-item">
                  <Button type="primary" ghost icon="ios-search" @click="search_show=!search_show">搜索</Button>
               </div>
               <div class="g-header-item">
                  <Button icon="md-refresh" @click="index(1)" :disabled="table_loading">刷新</Button>
               </div>
            </li>
         </ul>
         <ul class="g-handle" v-show="search_show">
            <li class="g-handle-item">
               <Input placeholder="请选择车辆" clearable readonly v-model.trim="params.plateNo" @click.native="carSelectChange($event)"/>
            </li>
            <li class="g-handle-item w-180">
               <select-tree v-model="params.deptId"
                            placeholder="请选择车辆组"
                            clearable
                            filterable
                            :default-expanded-keys="departmentsDefaultExpandedKeys"
                            :data="departments">
               </select-tree>
            </li>
            <li class="g-handle-item w-150">
               <el-select placeholder="请选择操作类型" clearable size="mini" v-model="params.cardState">
                  <el-option value="1" label="插卡"></el-option>
                  <el-option value="2" label="拔卡"></el-option>
               </el-select>
            </li>
            <li class="g-handle-item">
               <DatePicker class="w-300 hide" placeholder="请选择时间" type="datetimerange" :clearable="false" :editable="false" :options="datePickerOptions" v-model.trim="params.date"></DatePicker>
            </li>
            <li class="g-handle-item">
               <Button type="primary" ghost @click="index(1)" :disabled="table_loading">立即搜索</Button>
            </li>
            <li class="g-handle-item">
               <Button type="warning" ghost @click="resetSearch" :disabled="table_loading">重置搜索</Button>
            </li>
         </ul>
      </div>

      <div class="g-content">
         <el-table id="elTable" ref="table" border stripe highlight-current-row :show-overflow-tooltip="true" :height="table.height"
                   v-loading="table_loading" :data="(data?data.rows:[])" @header-dragend="tableDoLayout">
            <el-table-column label="车牌号" prop="plateNo" min-width="100" show-overflow-tooltip></el-table-column>
            <el-table-column label="车组" prop="deptName" min-width="160" show-overflow-tooltip></el-table-column>
            <el-table-column label="IC卡状态" prop="" min-width="100" show-overflow-tooltip>
               <template slot-scope="{row,column,$index}">{{row.cardState==1?'插卡':'拔卡'}}</template>
            </el-table-column>
            <el-table-column label="读卡结果" prop="" min-width="100" show-overflow-tooltip>
               <template slot-scope="{row, column, $index}">{{row.readResult===0?'成功':'失败'}}</template>
            </el-table-column>
            <el-table-column label="操作时间" prop="" width="140" show-overflow-tooltip>
               <template slot-scope="{row, column, $index}">{{moment(row.operTime).format('YYYY-MM-DD HH:mm:ss')}}</template>
            </el-table-column>
            <el-table-column label="驾驶员" prop="driverName" min-width="100" show-overflow-tooltip></el-table-column>
            <el-table-column label="驾驶证" prop="certificationCode" min-width="160" show-overflow-tooltip></el-table-column>
            <el-table-column label="发证机构" prop="agencyName" min-width="100" show-overflow-tooltip></el-table-column>
            <el-table-column label="证件有效期" prop="validateDate" width="140" show-overflow-tooltip></el-table-column>
            <el-table-column label="创建时间" prop="" width="140" show-overflow-tooltip>
               <template slot-scope="{row, column, $index}">{{moment(row.createDate).format('YYYY-MM-DD HH:mm:ss')}}</template>
            </el-table-column>
         </el-table>
      </div>

      <div class="g-page" id="tablePaging">
         <Page :show-total="true" :show-elevator="true" :show-sizer="true" :page-size="params.rows" :page-size-opts="table.size_options"
               :current="params.page" :total="data?data.total:0" @on-change="index($event)" @on-page-size-change="pageSizeChange">
         </Page>

         <!--<el-pagination layout="total, sizes, prev, pager, next, jumper" :page-size="params.rows" :page-sizes="table.size_options"-->
               <!--:current-page="params.page" :total="data?data.total:0" @current-change="index($event)" @size-change="pageSizeChange">-->
         <!--</el-pagination>-->
      </div>
      <car-select v-model="car_select_show" @row-dblclick="carSelectParams"></car-select>
   </div>
</template>

<script src="./js/index.js"></script>
