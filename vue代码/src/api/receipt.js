import request from "@/utils/request";


export const outInReceipt =  (receiptInfo) => {
    return request.post("/receipt/outInReceipt",receiptInfo)
}

export const getReceipts =  (pageInfo) => {
    return request.post("receipt/getReceipts",pageInfo)
}

export const deleteReceipts =  (receiptInfo) => {
    return request.post("receipt/deleteReceipt",receiptInfo)
}