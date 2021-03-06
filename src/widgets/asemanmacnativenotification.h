/*
    Copyright (C) 2017 Aseman Team
    http://aseman.co

    AsemanQtTools is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    AsemanQtTools is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

#ifndef ASEMANMACNATIVENOTIFICATION_H
#define ASEMANMACNATIVENOTIFICATION_H

#include <QObject>
#include <QStringList>
#include <QColor>

#include "asemanwidgets_global.h"

class QDBusMessage;
class AsemanMacNativeNotificationPrivate;
class LIBQTASEMAN_WIDGETS_EXPORT AsemanMacNativeNotification : public QObject
{
    Q_OBJECT
    Q_PROPERTY(QColor color READ color WRITE setColor NOTIFY colorChanged)

public:
    AsemanMacNativeNotification(QObject *parent = Q_NULLPTR);
    virtual ~AsemanMacNativeNotification();

    void setColor(const QColor &color);
    QColor color() const;

public Q_SLOTS:
    uint sendNotify(const QString & title, const QString & body, const QString & icon, uint replace_id = 0, int timeOut = 3000 , const QStringList &actions = QStringList());
    void closeNotification( uint id );

Q_SIGNALS:
    void notifyClosed( uint id );
    void notifyTimedOut( uint id );
    void notifyAction( uint id, const QString & action );
    void colorChanged();

private Q_SLOTS:
    void messageClicked();
    void messageDestroyed();

private:
    AsemanMacNativeNotificationPrivate *p;
};

typedef AsemanMacNativeNotification QAsemanMacNativeNotification;

#endif //ASEMANMACNATIVENOTIFICATION_H
