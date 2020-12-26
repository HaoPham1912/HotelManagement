<template>
  <div class="container">
    <div class="row">
      <div class="col-xs-12">
        <div class="invoice-title">
          <h2>Invoice</h2>
          <h3 class="pull-right"># 1</h3>
        </div>
        <hr />
        <div class="row">
          <!-- <div class="col-xs-6">
            <address>
              <strong>Payment Method:</strong><br />
              Visa ending **** 4242<br />
              jsmith@email.com
            </address>
          </div> -->
          <div class="col-xs-6 text-right">
            <address>
              <strong>Order Date:</strong><br />
              March 7, 2014<br /><br />
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
                    <td><strong>Room Id</strong></td>
                    <td class="text-center"><strong>Book Date</strong></td>
                    <td class="text-center"><strong>Check In Date</strong></td>
                    <td class="text-right"><strong>Check Out Date</strong></td>
                    <td class="text-right">
                      <strong>Total Price Paid</strong>
                    </td>
                  </tr>
                </thead>
                <tbody>
                  <!-- foreach ($order->lineItems as $line) or some such thing here -->
                  <tr>
                    <td>1</td>
                    <td class="text-center">19/12/2020</td>
                    <td class="text-center">21/12/2020</td>
                    <td class="text-right">22/12/2020</td>
                    <td class="text-right">$335,45</td>
                  </tr>
                  <tr>
                    <td>5</td>
                    <td class="text-center">19/12/2020</td>
                    <td class="text-center">21/12/2020</td>
                    <td class="text-right">22/12/2020</td>
                    <td class="text-right">$335,45</td>
                  </tr>

                  <tr>
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
                  </tr>
                  <tr>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line"></td>
                    <td class="no-line text-center"><strong>Total</strong></td>
                    <td class="no-line text-right">$670.48</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
    <button style="float: right;" class="btn btn-primary">EXPORT</button>
  </div>
</template>
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
