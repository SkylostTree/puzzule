const puzzleContainer = document.getElementById('puzzleContainer');
let puzzle = [];

function createPuzzle(layout) {
  puzzleContainer.innerHTML = ''; // 清空之前的拼图
  
  const rows = layout.trim().split('\n');
  puzzle = rows.map(row => row.trim().split(/\s+/).map(Number));
  
  puzzle.forEach((row, rowIndex) => {
    row.forEach((number, colIndex) => {
      const piece = document.createElement('div');
      const pieceInner = document.createElement('div');
      pieceInner.className = 'inner'
      piece.classList.add('puzzle-piece');
      pieceInner.textContent = number === 0 ? '' : number;
      piece.dataset.row = rowIndex;
      piece.dataset.col = colIndex;
      piece.id = 'x' + number
      piece.style.left = `${colIndex * 100}px`; // 使用绝对定位布局
      piece.style.top = `${rowIndex * 100}px`; // 使用绝对定位布局
      piece.appendChild(pieceInner)
      puzzleContainer.appendChild(piece);
    });
  });
}

function applyLayout() {
  const layoutInput = document.getElementById('layoutInput');
  createPuzzle(layoutInput.value);
}


function sleep (time) {
  return new Promise(resolve => {
    setTimeout(()=> {
      resolve()
    }, time)
  })
}



async function executeSteps(fast = false) {
  const stepsInput = document.getElementById('stepsInput');
  const steps = stepsInput.value.trim().split('\n');
  
  for (let i=0; i<steps.length; i++) {
    const step = steps[i]
    const [from, to] = step.split('->').map(Number);
    movePiece(from, to);
    if (!fast) {
      await sleep(700)
    }
  }
}

function movePiece(fromNumber, toNumber) {
  const fromPiece = document.getElementById(`x${fromNumber}`);
  const toPiece = document.getElementById(`x${toNumber}`);
  console.log(`x${fromNumber}`)
  
  if (fromPiece && toPiece) {
    // 交换位置
    const tempTop = fromPiece.style.top;
    const tempLeft = fromPiece.style.left;
    fromPiece.style.top = toPiece.style.top;
    fromPiece.style.left = toPiece.style.left;
    toPiece.style.top = tempTop;
    toPiece.style.left = tempLeft;
    
    // 更新拼图数据
    // const fromRow = parseInt(fromPiece.dataset.row);
    // const toRow = parseInt(toPiece.dataset.row);
    // puzzle[fromRow][0] = toNumber;
    // puzzle[toRow][0] = fromNumber;
    //
    // // 更新拼图界面
    // fromPiece.dataset.row = toRow;
    // toPiece.dataset.row = fromRow;
  } else {
    console.error('找不到拼图块元素');
  }
}

init()

function init() {
  
  const layoutInput = document.getElementById('layoutInput');
  layoutInput.value = `1 3 4
8 0 5
7 6 2
`
  applyLayout()
  
  
  const steps = `3->0
1->0
8->0
7->0
6->0
2->0
5->0
3->0
2->0
5->0
3->0
4->0
1->0
8->0
7->0
6->0
5->0
3->0
4->0
2->0
3->0
4->0
2->0
1->0
8->0
7->0
6->0
5->0
4->0
3->0
5->0
4->0
3->0
2->0
1->0
8->0
7->0
6->0
4->0
5->0
6->0
4->0
5->0
6->0
8->0
7->0
4->0
5->0
6->0
3->0
2->0
1->0
7->0
4->0
5->0
8->0
4->0
5->0
8->0
6->0
3->0
2->0
1->0
4->0
5->0
8->0
6->0
3->0
2->0
1->0
4->0
7->0
8->0
6->0
3->0
2->0
1->0
4->0
7->0
8->0`
  
  const stepsInput = document.getElementById('stepsInput');
  stepsInput.value = steps
}
