
module.exports = function(passport){
    var route=require('express').Router();

route.post('/local',
    passport.authenticate('local', {
        successRedirect: '/start',
        failureRedirect: '/login',
        failureFlash: false
    })
);




route.post('/register', function (req, res) {

    hasher({ password: req.body.password }, function (err, pass, salt, hash) {
        pool.getConnection(function (err, conn) {
            var sql = "select userId from user where userId like 'local%' order by (substring_index(userId, ':',-1)+0) desc limit 1";
            conn.query(sql, function (err, result) {
                if (err) { console.log('select error : ', err); return; }
                number = result[0].userId.split(':');
                number = number[1];
                number++;
                console.log(number);
                var user = {
                    userId: 'local:' + number,
                    email: req.body.email,
                    displayName: req.body.displayName,
                    password: hash,
                    gender: req.body.gender,
                    salt: salt,
                }
                pool.getConnection(function (err, conn) {
                    var sql = 'select email from user where email=?';
                    conn.query(sql, user.email, function (err, result) {
                        if (result == '') {
                            pool.getConnection(function (err, conn) {
                                if (err) {
                                    console.log('getConnection error : ', err);
                                    return;
                                }
                                var sql = 'insert into user set ?'
                                var query = conn.query(sql, user, function (err, result) {
                                    if (err) {
                                        console.log('register query fail : ', err);
                                        return;
                                    }
                                    
                                    console.log('유저 추가 : ', user);
                                    number++;
                                    req.login(user, function (err) {
                                        req.session.save(function () {
                                            res.redirect('/start');
                                        })
                                    })
                                    conn.release();
                                });

                            });

                        }
                        else{console.log('중복된 메일입니다.');res.redirect('/login');}
                    })
                })

            });
        });

    });
});
route.get('/login', function (req, res) {
    res.send('login page');
})
route.get('/logout', function (req, res) {
    req.logout();
    res.redirect(' /login');
});

//페이스북 로그인 시작

route.get('/facebook', passport.authenticate('facebook'));
route.get('/facebook/callback',
  passport.authenticate('facebook', { successRedirect: '/start',
                                      failureRedirect: '/login' }));

    return route;
}

