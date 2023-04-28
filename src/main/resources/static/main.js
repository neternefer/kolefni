window.onload = function() {
    const electricityCalculator = document.querySelector('#electricity-calculator');
    const waterCalculator = document.querySelector('#water-calculator');
    const waterSwitch = document.querySelector('#water');
    const electricitySwitch = document.querySelector('#el');

    electricitySwitch.addEventListener('click', () => {
        electricityCalculator.classList.add('active');
        waterCalculator.classList.remove('active');
        electricitySwitch.classList.add('clicked');
        waterSwitch.classList.remove('clicked');
    });

    waterSwitch.addEventListener('click', () => {
        waterCalculator.classList.add('active');
        electricityCalculator.classList.remove('active');
        waterSwitch.classList.add('clicked');
        electricitySwitch.classList.remove('clicked');
    });
}
