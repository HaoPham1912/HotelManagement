<template>
  <div class="container">
    <div class="row">
      <div class="col-xs-12">
        <div class="invoice-title">
          <h2>Invoice</h2>
          <h3 class="pull-right">{{ `# ${this.billId}` }}</h3>
        </div>
        <hr />
        <div class="row">
          <div class="col-xs-6 text-right">
            <address>
              <strong>Export Date:</strong><br />
              {{ printDate }}<br /><br />
            </address>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title"><strong>Booking summary</strong></h3>
          </div>
          <div class="panel-body">
            <div class="table-responsive">
              <table class="table table-condensed">
                <thead>
                  <tr>
                    <td class="text-center"><strong>Bill Id</strong></td>
                    <td class="text-center"><strong>Room Id</strong></td>
                    <td class="text-right"><strong>Book Date</strong></td>
                    <td class="text-right"><strong>Check In Date</strong></td>
                    <td class="text-right"><strong>Check Out Date</strong></td>
                    <td class="text-right">
                      <strong>Total Price Paid</strong>
                    </td>
                  </tr>
                </thead>
                <tbody>
                  <!-- foreach ($order->lineItems as $line) or some such thing here -->
                  <tr v-for="(data, index) in bookingList" :key="index">
                    <td class="text-center">{{ data.billId }}</td>
                    <td class="text-center">{{ data.roomId }}</td>
                    <td class="text-right">{{ data.bookDate }}</td>
                    <td class="text-right">{{ data.checkinDate }}</td>
                    <td class="text-right">{{ data.checkoutDate }}</td>
                    <td class="text-right">{{ data.paidPrice }}</td>
                  </tr>

                  <!-- <tr>
                    <td class="thick-line"></td>
                    <td class="thick-line"></td>
                    <td class="thick-line"></td>
                    <td class="thick-line text-center">
                      <strong>Subtotal</strong>
                    </td>
                    <td class="thick-line text-right">$670.99</td>
                  </tr>
                  <tr>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line text-center">
                      <strong>Promotion Price</strong>
                    </td>
                    <td class="no-line text-right">$15</td>
                  </tr> -->
                  <tr>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line text-center"><strong>Total</strong></td>
                    <td class="no-line text-right">{{ totalPrice }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
    <canvas id="canvas" width="500" height="700"></canvas>
    <button style="float: right;" class="btn btn-primary" @click="download">
      PRINT
    </button>
  </div>
</template>

<script>
import BillService from '../../services/BillService';
import RoomService from '../../services/RoomService';
import jspdf from 'jspdf';
// /import html2canvas from 'html2canvas';
import { AUTH_LOGOUT } from '../../store/actions/auth';
export default {
  data() {
    return {
      billId: this.$route.params.id,
      bookingList: [],
      bookDate: '',

      totalPrice: 0,

      currentBill: {},

      currentRoomId: [],

      roomInBill: [],

      printDate: '',
    };
  },

  methods: {
    getBookingList() {
      BillService.getBookingByBillId(this.billId)
        .then((response) => {
          console.log(response.data);
          this.bookingList = response.data;
          this.bookingList.forEach((element) => {
            this.totalPrice += element.paidPrice;
            this.currentRoomId.push(element.roomId);
          });
          if (Object.entries(this.bookingList).length === 0) {
            alert('Session time out!!!');
            this.$store
              .dispatch(AUTH_LOGOUT)
              .then(() => this.$router.push('/login'));
          }
          console.log(this.currentRoomId);
          this.currentRoomId.forEach((element) => {
            console.log(`element ${element}`);
            RoomService.getRoomById(element)
              .then((response) => {
                this.roomInBill.push(response.data);
              })
              .catch((e) => {
                console.log(e);
              });
          });
          console.log(this.roomInBill);
        })
        .catch((e) => {
          console.log(e);
        });

      BillService.getDetailBooking(this.billId)
        .then((response) => {
          console.log('current bill');
          console.log(response.data);
          this.currentBill = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    getRoomInforInBill() {
      this.currentRoomId.forEach((element) => {
        console.log(`element ${element}`);
        RoomService.getRoomById(element)
          .then((response) => {
            this.roomInBill.push(response.data);
          })
          .catch((e) => {
            console.log(e);
          });
      });
      console.log(this.roomInBill);
    },

    getPrintDate() {
      var today = new Date();
      var dd = String(today.getDate()).padStart(2, '0');
      var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
      var yyyy = today.getFullYear();

      this.printDate = dd + '/' + mm + '/' + yyyy;
    },

    download() {
      var pdf = new jspdf('p', 'mm', [200, 120]);
      var index = 10;

      pdf.text('H2 HOTEL', 50, index);
      pdf.text('CUSTOMER BILL', 10, (index += 10));
      pdf.text(`Date Export: ${this.printDate}`, 10, (index += 10));
      pdf.text(
        `Customer Name: ${this.currentBill.customerName}`,
        10,
        (index += 10)
      );
      for (let i = 0; i < this.bookingList.length; i++) {
        console.log(this.bookingList[i].roomId);
        pdf.text('------------------------------', 10, (index += 10));
        pdf.text(`Room Name: ${this.roomInBill[i].name}`, 10, (index += 10));
        pdf.text(
          `Booking Date: ${this.bookingList[i].bookDate}`,
          10,
          (index += 10)
        );
        pdf.text(
          `Checkin Date: ${this.bookingList[i].checkinDate}`,
          10,
          (index += 10)
        );
        pdf.text(
          `Checkout Date: ${this.bookingList[i].checkoutDate}`,
          10,
          (index += 10)
        );
        pdf.text(`Price: ${this.bookingList[i].paidPrice}`, 10, (index += 10));
      }
      pdf.text('------------------------------', 10, (index += 10));
      pdf.text(`Total Price ${this.totalPrice}`, 10, (index += 10));
      pdf.text('------------------------------', 10, (index += 10));
      pdf.text('Signature', 10, (index += 10));
      var today = new Date();
      var date =
        today.getFullYear() +
        '-' +
        (today.getMonth() + 1) +
        '-' +
        today.getDate();
      var time =
        today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
      var dateTime = date + ' ' + time;
      pdf.save(`Bill at ${dateTime}.pdf`);
    },
  },

  mounted() {
    this.getBookingList();
    this.getPrintDate();
  },
};
</script>

<style scoped>
.invoice-title h2,
.invoice-title h3 {
  display: inline-block;
}

.table > tbody > tr > .no-line {
  border-top: none;
}

.table > thead > tr > .no-line {
  border-bottom: none;
}

.table > tbody > tr > .thick-line {
  border-top: 2px solid;
}
</style>
