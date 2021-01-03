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
    <button style="float: right;" class="btn btn-primary">PRINT</button>
  </div>
</template>

<script>
import BillService from '../../services/BillService';

export default {
  data() {
    return {
      billId: this.$route.params.id,
      bookingList: [],
      bookDate: '',

      totalPrice: 0,

      printDate: '',
    };
  },

  methods: {
    getBookingList() {
      BillService.getBookingByBillId(this.billId).then((response) => {
        console.log(response.data);
        this.bookingList = response.data;
        this.bookingList.forEach((element) => {
          this.totalPrice += element.paidPrice;
        });
      });
    },

    getPrintDate() {
      var today = new Date();
      var dd = String(today.getDate()).padStart(2, '0');
      var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
      var yyyy = today.getFullYear();

      this.printDate = mm + '/' + dd + '/' + yyyy;
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
