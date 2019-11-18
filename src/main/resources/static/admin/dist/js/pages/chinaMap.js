    $(function(){
      $('#world-map-markers').vectorMap({
        map: 'cn_mill',
        backgroundColor:'white',
        regionStyle: {
          initial: {
            fill: 'grey'
          }
        },
		
        zoomOnScroll: true,
        zoomMax: 10,
        zoomMin: 1
      });
    });