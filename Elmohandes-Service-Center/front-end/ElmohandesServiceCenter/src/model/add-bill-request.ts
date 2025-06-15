import {Bill} from "./bill";
import {BillFields} from "./bill-fields";

export interface AddBillRequest {
  billDto: Bill;
  billFieldsDtos: BillFields[];
}
