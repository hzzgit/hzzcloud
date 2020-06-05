package com.hzz.hzzcloud.fangkuai;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener{
    private int mapRow = 21;
    private int mapCol = 12;
    private int mapGame[][] = new int[mapRow][mapCol];//开辟一个二维数组空间，用来存放我们的地图信息

    private Timer timer;
    private int score = 0;//记录成绩
    Random random = new Random();
    private int curShapeType = -1;
    private int curShapeState = -1;//设置当前的形状类型和当前的形状状态
    private int nextShapeType = -1;
    private int nextShapeState = -1;//设置下一次出现的方块组的类型和状态

    private int posx = 0;
    private int posy = 0;

    private final int shapes[][][] = new int[][][]{
            //T字形按逆时针的顺序存储
            {
                    {0,1,0,0, 1,1,1,0, 0,0,0,0, 0,0,0,0},
                    {0,1,0,0, 1,1,0,0, 0,1,0,0, 0,0,0,0},
                    {1,1,1,0, 0,1,0,0, 0,0,0,0, 0,0,0,0},
                    {0,1,0,0, 0,1,1,0, 0,1,0,0, 0,0,0,0}
            },
            //I字形按逆时针的顺序存储
            {
                    {0,0,0,0, 1,1,1,1, 0,0,0,0, 0,0,0,0},
                    {0,1,0,0, 0,1,0,0, 0,1,0,0, 0,1,0,0},
                    {0,0,0,0, 1,1,1,1, 0,0,0,0, 0,0,0,0},
                    {0,1,0,0, 0,1,0,0, 0,1,0,0, 0,1,0,0}
            },
            //倒Z形按逆时针的顺序存储
            {
                    {0,1,1,0, 1,1,0,0, 0,0,0,0, 0,0,0,0},
                    {1,0,0,0, 1,1,0,0, 0,1,0,0, 0,0,0,0},
                    {0,1,1,0, 1,1,0,0, 0,0,0,0, 0,0,0,0},
                    {1,0,0,0, 1,1,0,0, 0,1,0,0, 0,0,0,0}
            },
            //Z形按逆时针的顺序存储
            {
                    {1,1,0,0, 0,1,1,0, 0,0,0,0, 0,0,0,0},
                    {0,1,0,0, 1,1,0,0, 1,0,0,0, 0,0,0,0},
                    {1,1,0,0, 0,1,1,0, 0,0,0,0, 0,0,0,0},
                    {0,1,0,0, 1,1,0,0, 1,0,0,0, 0,0,0,0}
            },
            //J字形按逆时针的顺序存储
            {
                    {0,1,0,0, 0,1,0,0, 1,1,0,0, 0,0,0,0},
                    {1,1,1,0, 0,0,1,0, 0,0,0,0, 0,0,0,0},
                    {1,1,0,0, 1,0,0,0, 1,0,0,0, 0,0,0,0},
                    {1,0,0,0, 1,1,1,0, 0,0,0,0, 0,0,0,0}
            },
            //L字形按逆时针的顺序存储
            {
                    {1,0,0,0, 1,0,0,0, 1,1,0,0, 0,0,0,0},
                    {0,0,1,0, 1,1,1,0, 0,0,0,0, 0,0,0,0},
                    {1,1,0,0, 0,1,0,0, 0,1,0,0, 0,0,0,0},
                    {1,1,1,0, 1,0,0,0, 0,0,0,0, 0,0,0,0}
            },
            //田字形按逆时针的顺序存储
            {
                    {1,1,0,0, 1,1,0,0, 0,0,0,0, 0,0,0,0},
                    {1,1,0,0, 1,1,0,0, 0,0,0,0, 0,0,0,0},
                    {1,1,0,0, 1,1,0,0, 0,0,0,0, 0,0,0,0},
                    {1,1,0,0, 1,1,0,0, 0,0,0,0, 0,0,0,0}
            }
    };
    private int rowRect = 4;
    private int colRect = 4;//这里我们把存储的图像看成是一个4*4的二维数组，虽然在上面我们采用一维数组来存储，但实际还是要看成二维数组来实现
    private int RectWidth = 10;

    public GamePanel()//构造函数----创建好地图
    {
        CreateRect();
        initMap();//初始化这个地图
        SetWall();//设置墙
// CreateRect();
        timer = new Timer(500,new TimerListener());
        timer.start();
    }

    class TimerListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            MoveDown();
        }
    }

    public void SetWall()//第0列和第11列都是墙，第20行也是墙
    {
        for(int i = 0; i < mapRow; i++)//先画列
        {
            mapGame[i][0] = 2;
            mapGame[i][11] = 2;
        }
        for(int j = 1; j < mapCol-1; j++)//画最后一行
        {
            mapGame[20][j] = 2;
        }
    }

    public void initMap()//初始化这个地图，墙的ID是2，空格的ID是0，方块的ID是1
    {
        for(int i = 0; i < mapRow; i++)
        {
            for(int j = 0; j < mapCol; j++)
            {
                mapGame[i][j] = 0;
            }
        }
    }

    public void CreateRect()//创建方块---如果当前的方块类型和状态都存在就设置下一次的，如果不存在就设置当前的并且设置下一次的状态和类型
    {
        if(curShapeType == -1 && curShapeState == -1)//当前的方块状态都为1，表示游戏才开始
        {
            curShapeType = random.nextInt(shapes.length);
            curShapeState = random.nextInt(shapes[0].length);
        }
        else
        {
            curShapeType = nextShapeType;
            curShapeState = nextShapeState;
        }
        nextShapeType = random.nextInt(shapes.length);
        nextShapeState = random.nextInt(shapes[0].length);
        posx = 0;
        posy = 1;//墙的左上角创建方块
        if(GameOver(posx,posy,curShapeType,curShapeState))
        {
            JOptionPane.showConfirmDialog(null, "游戏结束！", "提示", JOptionPane.OK_OPTION);
            System.exit(0);
        }
    }


    public boolean GameOver(int x, int y, int ShapeType, int ShapeState)//判断游戏是否结束
    {
        if(IsOrNoMove(x,y,ShapeType,ShapeState))
        {
            return false;
        }
        return true;
    }

    public boolean IsOrNoMove(int x, int y, int ShapeType, int ShapeState)//判断当前的这个图形是否可以移动,这里重点强调x,y的坐标是指4*4的二维数组（描述图形的那个数组）的左上角目标
    {
        for(int i = 0; i < rowRect ; i++)
        {
            for(int j = 0; j < colRect; j++)
            {
                if(shapes[ShapeType][ShapeState][i*colRect+j] == 1 && mapGame[x+i][y+j] == 1
                        || shapes[ShapeType][ShapeState][i*colRect+j] == 1 && mapGame[x+i][y+j] == 2)
                {
                    return false;
                }
            }
        }
        return true;
    }


    public void Turn()//旋转
    {
        int temp = curShapeState;
        curShapeState = (curShapeState+1) % shapes[0].length;
        if(IsOrNoMove(posx,posy,curShapeType,curShapeState))
        {
        }
        else
        {
            curShapeState = temp;
        }
        repaint();
    }

    public void MoveDown()//向下移动
    {
        if(IsOrNoMove(posx+1,posy,curShapeType,curShapeState))
        {
            posx++;
        }
        else
        {
            AddToMap();//将此行固定在地图中
            CheckLine();
            CreateRect();//重新创建一个新的方块
        }
        repaint();
    }

    public void MoveLeft()//向左移动
    {
        if(IsOrNoMove(posx,posy-1,curShapeType,curShapeState))
        {
            posy--;
        }
        repaint();
    }

    public void MoveRight()//向右移动
    {
        if(IsOrNoMove(posx,posy+1,curShapeType,curShapeState))
        {
            posy++;
        }
        repaint();
    }

    public void AddToMap()//固定掉下来的这一图像到地图中
    {
        for(int i = 0; i < rowRect; i++)
        {
            for(int j = 0; j < colRect; j++)
            {
                if(shapes[curShapeType][curShapeState][i*colRect+j] == 1)
                {
                    mapGame[posx+i][posy+j] = shapes[curShapeType][curShapeState][i*colRect+j];
                }
            }
        }
    }

    public void CheckLine()//检查一下这些行中是否有满行的
    {
        int count = 0;
        for(int i = mapRow-2; i >= 0; i--)
        {
            count = 0;
            for(int j = 1; j < mapCol-1; j++)
            {
                if(mapGame[i][j] == 1)
                {
                    count++;
                }
                else
                    break;
            }
            if(count >= mapCol-2)
            {
                for(int k = i; k > 0; k--)
                {
                    for(int p = 1; p < mapCol-1; p++)
                    {
                        mapGame[k][p] = mapGame[k-1][p];
                    }
                }
                score += 10;
                i++;
            }
        }
    }

    public void paint(Graphics g)//重新绘制窗口
    {
        super.paint(g);
        for(int i = 0; i < rowRect; i++)//绘制正在下落的方块
        {
            for(int j = 0; j < colRect; j++)
            {
                if(shapes[curShapeType][curShapeState][i*colRect+j] == 1)
                {
                    g.fillRect((posy+j+1)*RectWidth, (posx+i+1)*RectWidth, RectWidth, RectWidth);
                }
            }
        }
        for(int i = 0; i < mapRow; i++)//绘制地图上面已经固定好的方块信息
        {
            for(int j = 0; j < mapCol; j++)
            {
                if(mapGame[i][j] == 2)//画墙
                {
                    g.drawRect((j+1)*RectWidth, (i+1)*RectWidth, RectWidth, RectWidth);
                }
                if(mapGame[i][j] == 1)//画小方格
                {
                    g.fillRect((j+1)*RectWidth, (i+1)*RectWidth, RectWidth, RectWidth);
                }
            }
        }
        g.drawString("score = "+ score, 225, 15);
        g.drawString("下一个方块：", 225, 50);
        for(int i = 0; i < rowRect; i++)
        {
            for(int j = 0; j < colRect; j++)
            {
                if(shapes[nextShapeType][nextShapeState][i*colRect+j] == 1)
                {
                    g.fillRect(225+(j*RectWidth), 100+(i*RectWidth), RectWidth, RectWidth);
                }
            }
        }
    }

    public void NewGame()//游戏重新开始
    {
        score = 0;
        initMap();
        SetWall();
        CreateRect();
        repaint();
    }

    public void StopGame()//游戏暂停
    {
        timer.stop();
    }

    public void ContinueGame()
    {
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP://上----旋转
                Turn();
                break;
            case KeyEvent.VK_DOWN://下----向下移动
                MoveDown();
                break;
            case KeyEvent.VK_LEFT://左----向左移动
                MoveLeft();
                break;
            case KeyEvent.VK_RIGHT://右----向右移动
                MoveRight();
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}

