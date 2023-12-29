import { createRouter,createWebHashHistory} from "vue-router"

import Login from "@/components/app/content/Login.vue";
import Register from "@/components/app/content/Register.vue";
import UserMain from "@/components/app/content/userInfo/UserMain.vue";
import Contenter from "@/components/app/Contenter.vue";
import Main from "@/components/app/content/Main.vue";
import Message from "@/components/app/content/userInfo/Message.vue";
import updatePassword from "@/components/app/content/userInfo/updatePassword.vue";
import drugStock from "@/components/app/content/drugInfo/DrugStock.vue";
import Supplier from "@/components/app/content/supplierInfo/Supplier.vue";
import DrugAdd from "@/components/app/content/drugInfo/DrugAdd.vue";
import Test from "@/test/test.vue";
import Upload_UserMain from "@/components/app/content/userInfo/Upload_UserMain.vue";
import DrugUpdate from "@/components/app/content/drugInfo/DrugUpdate.vue";
import SupplierAdd from "@/components/app/content/supplierInfo/supplierAdd.vue";
import ProblemList from "@/components/app/content/problemInfo/ProblemList.vue";
import SupplierUpdate from "@/components/app/content/supplierInfo/SupplierUpdate.vue";
import ReceiptAdd from "@/components/app/content/receiptInfo/ReceiptAdd.vue";
import ReceiptList from "@/components/app/content/receiptInfo/ReceiptList.vue";


const router = createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path:'/',
            component:Contenter,
            children:[
                {
                    path:'/',
                    component:Main
                },
                {
                    name:'login',
                    path:'login',
                    component:Login
                },
                {
                    name:'main',
                    path:'main',
                    component: Main,
                    children:[
                        {
                            name: 'userMain',
                            path: 'userMain',
                            component: UserMain
                        },
                        {
                            name: 'upload_UserMain',
                            path: 'upload_UserMain',
                            component: Upload_UserMain
                        },
                        {
                            name: 'Message',
                            path: 'Message',
                            component: Message
                        },
                        {
                            name: 'updatePassword',
                            path: 'updatePassword',
                            component: updatePassword
                        },
                        {
                            name: 'drugStock',
                            path: 'drugStock',
                            component: drugStock
                        },
                        {
                            name: 'drugAdd',
                            path: 'drugAdd',
                            component: DrugAdd
                        },
                        {
                            name: 'drugUpdate',
                            path: 'drugUpdate',
                            component: DrugUpdate
                        },
                        {
                            name: 'supplier',
                            path: 'supplier',
                            component: Supplier
                        },
                        {
                            name: 'supplierAdd',
                            path: 'supplierAdd',
                            component: SupplierAdd
                        },
                        {
                            name: 'supplierUpdate',
                            path: 'supplierUpdate',
                            component: SupplierUpdate
                        },
                        {
                            name: 'problemList',
                            path: 'problemList',
                            component: ProblemList
                        },
                        {
                            name: 'receiptAdd',
                            path: 'receiptAdd',
                            component: ReceiptAdd
                        },
                        {
                            name: 'receiptList',
                            path: 'receiptList',
                            component: ReceiptList
                        },
                        {
                            name: 'test',
                            path: 'test',
                            component: Test
                        },
                    ]
                },
                {
                    name:'register',
                    path:'/register',
                    component:Register
                },
            ]
        },
    ]
})

//对外暴露路由对象
export default router;