export interface Bill {
  id?: number;
  dateOfNow: string;
  receptionDate: string;
  deliveryDate: string;
  customerRepresentative: string;
  lastServiceDate: string;
  factory: number;
  spareParts: number;
  externalWorks: number;
  taxes: number;
  factoryDiscount: number;
  sparePartsDiscount: number;
  specialDiscount: number;
  netBill: number;
  kilometers: string;
  customer_id: number;
  car_id: number;
}
