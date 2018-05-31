const distances_service = require('../services/distances_service');

exports.get_stores_in_area = function(req, res) {
    console.log(req.body);
    distances_service.findStoresNearUser(req.body, stores => {
        res.status(200).json(stores);
    });
    
};

exports.get_all = function(req, res) {

};