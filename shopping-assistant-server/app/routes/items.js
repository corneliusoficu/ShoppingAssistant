var express     = require('express');
var router      = express.Router();

var items_controller = require('../controllers/items_controller');

router.post('/', items_controller.create_item);

module.exports = router;