var express     = require('express');
var router      = express.Router();

var items_controller = require('../controllers/items_controller');

router.get('/category/:categoryId', items_controller.get_all)

module.exports = router;