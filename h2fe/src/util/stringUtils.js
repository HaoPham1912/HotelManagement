export default class StringUtil {
    makeUrl(path, requestParams, data, isArrayData) {
        //Started of Request param
        let url = path;
        if (requestParams && data) {
            url = path.concat('?');
            requestParams.forEach((item, index, array) => {
                //add key
                url = url.concat(item);

                url = url.concat('=');

                //add value
                if (isArrayData) {
                    url = url.concat(data[item] ? data[item] : '');
                } else {
                    url = url.concat(data);
                }

                //check last element
                if (index !== (array.length - 1)) {
                    url = url.concat('&');
                }
            });
        }
        return url;
    }

    getColumnData(listData, columnName) {
        let result = [];
        listData.forEach(function (item) {
            result.push(item[columnName]);
        });
        return result;
    }

    formatNumber(number) {
        return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    formatCurrencyNotDotDouble(value){
        if (!value || value === '' || value === '0' ) {
            return '0';
        }
        value = parseInt(value)
        value = Number(value)
            .valueOf()
            .toString()
        value = value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); 
        return value;
    }

    formatCurrency(value){
        if (!value || value === '' || value === '0' ) {
            return '0';
        }
        value = parseInt(value)
        value = Number(value)
            .valueOf()
            .toString()
        value = value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); 
        value = value + ".00";
        return value;
    }

    formatDate(date) {
        var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

        if (month.length < 2) 
            month = '0' + month;
        if (day.length < 2) 
            day = '0' + day;

        return [year, month, day].join('/');
    }
}