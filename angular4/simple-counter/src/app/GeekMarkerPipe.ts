import {Pipe, PipeTransform} from '@angular/core';

@Pipe({ name: 'geekMark' })
export class GeekMarkerPipe implements PipeTransform {
  transform(value: string, type: string): string {
    switch (value) {
      case 'A':
        return `Good A : ${value} `;
      case 'B':
        return `Normal B : ${value} `;
      case 'C':
        return `Bad C : ${value} `;
    }
  }
}


// 사용예
// {{name | geekMark:'A'}}
