var promise = require('bluebird');

var options = {
  // Initialization Options
  promiseLib: promise
};

var pgp = require('pg-promise')(options);
var connectionString = 'postgres://postgres:postgres@localhost:5432/teste';
var db = pgp(connectionString);

function readByCriteria(req, res, next) {
  db.any('select * from livro')
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retorna todos os livros'
        });
    })
    .catch(function (err) {
      return next(err);
    });
}

function readByID(req, res, next) {
  var id = parseInt(req.params.id);
  db.one('select * from livro where id = $1', id)
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved ONE puppy'
        });
    })
    .catch(function (err) {
      return next(err);
    });
}

function create(req, res, next) {
  req.body.age = parseInt(req.body.age);
  db.none('insert into livro(nome, autor_fk, categoria_fk)' +
      'values(${nome}, ${autor_fk}, ${categoria_fk})',
    req.body)
    .then(function () {
      res.status(200)
        .json({
          status: 'success',
          message: 'Inserted one puppy'
        });
    })
    .catch(function (err) {
      return next(err);
    });
}

function update(req, res, next) {
  db.none('update livro set nome=$1, autor_fk=$2, categoria_fk=$3 where id=$4',
    [req.body.name, req.body.breed, parseInt(req.body.age),
      req.body.sex, parseInt(req.params.id)])
    .then(function () {
      res.status(200)
        .json({
          status: 'success',
          message: 'Updated puppy'
        });
    })
    .catch(function (err) {
      return next(err);
    });
}

function remove(req, res, next) {
  var pupID = parseInt(req.params.id);
  db.result('delete from livro where id = $1', pupID)
    .then(function (result) {
      /* jshint ignore:start */
      res.status(200)
        .json({
          status: 'success',
          message: `Removed ${result.rowCount} puppy`
        });
      /* jshint ignore:end */
    })
    .catch(function (err) {
      return next(err);
    });
}


module.exports = {
  readByCriteria: readByCriteria,
  readByID: readByID,
  create: create,
  update: update,
  remove: remove
};
