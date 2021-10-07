const express = require('express');
const app = express();
var pool = require('./routers/mysql/dbconfig.js');
var pool2 = require('./routers/mysql/dbconfig2.js');
const session = require('express-session');
var MySQLStore = require('express-mysql-session')(session);
var bodyParser = require('body-parser');
const pathUtil = require('path');
const fs = require('fs');
const ejs = require('ejs');
var request = require('request');
var parseString = require('xml2js').parseString;

app.set("view engine", 'ejs');
var now = new Date();
var success = { message: 'success' }
var fail = { message: 'fail' }

var passport = require('passport')
    , LocalStrategy = require('passport-local').Strategy;
app.use(session({
    secret: 'sdfDAW@$EDW&%WSxca&qqwq123adaW',
    resave: true,
    saveUninitialized: true,

    store: new MySQLStore({
        host: 'chkrdp2.cdmsishpmtue.ap-northeast-2.rds.amazonaws.com',
        port: 3306,
        user: 'chkrdp',
        password: 'cjs123rlf',
        database: 'session_animal'
    })
}));
app.use(express.static(__dirname + '/views'));

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(passport.initialize());
app.use(passport.session());

passport.serializeUser(function (user, done) {
    console.log('serializeUser : ', admin.adminId);
    done(null, admin.adminId);
});
passport.deserializeUser(function (id, done) {
    console.log('deserializeUser : ', id);
    pool.getConnection(function (err, conn) {
        if (err) { console.log('connection error : ', err); return; }
        var sql = 'select * from admin where adminId=?';
        conn.query(sql, id, function (err, result) {
            console.log('deserializeUser : ', result);
            if (err || result == '') { console.log('정보가 없습니다.'); conn.release(); return; }
            conn.release();
            return done(err, result);
        })
    });
});
passport.use(new LocalStrategy(
    function (username, password, done) {  //username -> email

        pool.getConnection(function (err, conn) {
            if (err) {
                console.log('getConnection error : ', err);
                return;
            }
            var sql = 'select *from admin where adminId = ?'
            conn.query(sql, username, function (err, result) {
                if (err || result == '') {
                    console.log('사용자 아이디 검색 불가 : ', err);
                    conn.release();
                    return done(null, false, { message: '사용자 정보가 존재하지 않습니다.' });
                }

                if (password == result[0].password) {
                    admin = {
                        adminId: result[0].adminId
                    }
                    done(null, admin);
                }  //로그인 성공 
                else {
                    console.log('비밀번호가 틀렸습니다.');
                    conn.release();
                    return done(null, false, { message: '비밀번호가 맞지 않습니다.' });
                }
            })
        })
    }

));


app.post('/auth/local',
    passport.authenticate('local'),
    function (req, res) {
        res.redirect('/member');
    });
app.get('/auth/local', function (req, res) {
    fs.readFile(__dirname + '/views/login.html', 'utf8', function (err, data) {
        res.send(ejs.render(data, {

        }));

    });
})
app.get('/auth/logout', function (req, res) {
    req.logout();
    res.redirect('/auth/local');
});


app.get('/member', function (req, res) {
    fs.readFile(__dirname + '/views/member.html', 'utf8', function (err, data) {
        pool.getConnection(function (error, conn) {
            var sql = 'select *from user'
            conn.query(sql, function (error, result) {
                if (error) { console.log("errorr : ", error); conn.release(); return; }
                res.send(ejs.render(data, {
                    data: result
                }));
            });
        });
    });
});
app.get('/main', function (req, res) {
    fs.readFile(__dirname + '/views/main.html', 'utf8', function (err, data) {
        pool.getConnection(function (error, conn) {
            var sql = 'select *from user'
            conn.query(sql, function (error, result) {
                if (error) { console.log("errorr : ", error); conn.release(); return; }
                res.send(ejs.render(data, {
                    data: result
                }));
            });
        });
    });
});
app.get('/admin', function (req, res) {
    fs.readFile(__dirname + '/views/table.html', 'utf8', function (err, data) {
        pool.getConnection(function (error, conn) {
            var sql = 'select *from user'
            conn.query(sql, function (error, result) {
                if (error) { console.log("errorr : ", error); conn.release(); return; }
                res.send(ejs.render(data, {
                    data: result
                }));
            });
        });
    });
});
app.get('/statistic', function (req, res) {
    pool.getConnection(function (error, conn) {
        var sql = "SELECT * FROM statistic order by statisticId desc limit 1";
        conn.query(sql, function (error, result) {
            if (error) {
                console.log('error : ', error); conn.release(); return;
            }
            res.send(result);
            conn.release();
        })
    })
})

function animal() {
    var url = 'http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic';
    var queryParams = '?' + encodeURIComponent('ServiceKey') + '=pFgcEMtxZueqIzaDsd4rZ4yVCaBNK2t68oBu3BUwEcNDsPU6tXmQGP79pqPJtgq3oLfNz42UgdO2cmu2s9nrlg%3D%3D'; /* Service Key*/
    queryParams += '&' + encodeURIComponent('bgnde') + '=' + encodeURIComponent('20170720'); /* 유기날짜 (검색 시작일) (YYYYMMDD) */
    queryParams += '&' + encodeURIComponent('endde') + '=' + encodeURIComponent('20170720'); /* 유기날짜 (검색 종료일) (YYYYMMDD) */
    queryParams += '&' + encodeURIComponent('upkind') + '=' + encodeURIComponent('417000'); /* 축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 : 429900 */
    queryParams += '&' + encodeURIComponent('kind') + '=' + encodeURIComponent(''); /* 품종코드 (품종 조회 OPEN API 참조) */
    queryParams += '&' + encodeURIComponent('upr_cd') + '=' + encodeURIComponent(''); /* 시도코드 (시도 조회 OPEN API 참조) */
    queryParams += '&' + encodeURIComponent('org_cd') + '=' + encodeURIComponent(''); /* 시군구코드 (시군구 조회 OPEN API 참조) */
    queryParams += '&' + encodeURIComponent('care_reg_no') + '=' + encodeURIComponent(''); /* 보호소번호 (보호소 조회 OPEN API 참조) */
    queryParams += '&' + encodeURIComponent('state') + '=' + encodeURIComponent('notice'); /* 상태 - 전체 : null(빈값) - 공고중 : notice - 보호중 : protect */
    // queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /* 페이지 번호 */
    //queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('null'); /* 페이지당 보여줄 개수 */

    request({
        url: url + queryParams,
        method: 'GET'
    }, function (error, response, body) {
        parseString(body,function(error,result){
            var data = JSON.stringify(result)
            console.log(data);
            console.log(data.length)
            console.log(data[item].length);
        })
        
    });
}
animal();


app.listen(3000, function () {
    console.log("success connecting server")
})