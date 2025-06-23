import { ScheduledTransaction } from "./scheduledTransaction";

export interface PaymentDateGroup {
  paymentDate: string;
  transactions: ScheduledTransaction[];
}
