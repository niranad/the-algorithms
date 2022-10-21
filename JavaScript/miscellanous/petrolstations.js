function truckTour(petrolpumps) {
  let len = petrolpumps.length,
    startPoint = 0,
    i = 0;
  let fuelValue, distCovered;

  while (i < len) {
    distCovered = 0;
    fuelValue = petrolpumps[i][0] - petrolpumps[i][1];
    if (fuelValue >= 0) {
      startPoint = i;
      distCovered++;
      travel(fuelValue, i + 1);

      if (distCovered === len) {
        break;
      }
    }
    i++;
  }

  function travel(fuelCapacity, point) {
    let count = 1;
    while (count < len) {
      if (point >= len) point = 0;
      fuelCapacity += petrolpumps[point][0] - petrolpumps[point][1];
      if (fuelCapacity >= 0) {
        distCovered++;
        point++;
        count++;
      } else {
        break;
      }
    }
  }

  return startPoint;}
