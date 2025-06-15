import {Customer} from "./customer";
import {Car} from "./car";
import {Bill} from "./bill";
import {BillFields} from "./bill-fields";

export interface FinalBillVm {
  customerDto: Customer;
  carDto: Car;
  billDto: Bill;
  billFieldsDtos: BillFields[];
}
