var express     = require('express');
var router      = express.Router();

var stores_controller = require('../controllers/stores_controller');

router.post('/inarea', stores_controller.get_stores_in_area)

module.exports = router;