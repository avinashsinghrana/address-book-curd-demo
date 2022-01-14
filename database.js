// npm install mysql
// mysql installed
// it should be configured
// user table -> while registration = 1
// booking -> mapping (user_id, flight_id), booking information related (date, fare, booking_date), select * from booking_details where user_id = 1 order by booking_date desc;
// flight details -> manual details
const {createPool} = require('mysql');

const pool = createPool({
    host: "localhost",
    user: "root",
    password: "credable",
    database: "lms",
    connectionLimit: 10
})
let myArray = [2, 4];
function addBookingDetails() {
    pool.query('insert into booking_details(\'user_id\', \'flight_id\', \'booking_date\') values(?, ?, now());', 1, 2, (err, result, fields) => {
        if (err) {
            return console.log(err);
        }
        return console.log(result);
    })
}

function getOrderDetail(user_id) {
    pool.query('select * from booking_details where user_id = ? order by booking_date desc', [user_id], (err, result, fields) => {
        if (err) {
            return console.log(err);
        }
        return console.log(result);
    })
}

function getOrderDetail(date){
    pool.query('select * from booking_details where booking_date = ? order by id desc', [date], (err, result, fields) => {
        if (err) {
            return console.log(err);
        }
        return console.log(result);
    })
}

function updateDetails(user_id, flight_id, id) {
    pool.query('update booking_details set user_id=?, filght_id=? where id = ?', user_id, flight_id, id, (err, result, fields) => {
        if (err) {
            return console.log(err);
        }
        return console.log(result);
    })
}
