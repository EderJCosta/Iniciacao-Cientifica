var express = require('express');
var router = express.Router();

var db = require('../queries/livrosQueries');


router.get('/api/livros', db.readByCriteria);
router.get('/api/livro/:id', db.readByID);
router.post('/api/livro/create', db.create);
router.put('/api/livro/:id', db.update);
router.delete('/api/livro/:id', db.remove);


module.exports = router;