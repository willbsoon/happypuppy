const mysql = require('mysql');

module.exports = mysql.createPool({
    host: 'chkrdp2.cdmsishpmtue.ap-northeast-2.rds.amazonaws.com',
    port: 3306,
    user: 'chkrdp',
    password: 'cjs123rlf',
    database: 'session_animal',
    connectionLimit: 300,
    waitForConnections: false
});
